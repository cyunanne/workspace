package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 1. 책 정보를 가져온다.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출기록 정보를 확인해서 대출중인지 확인한다.
        // 3. 대출중이라면 예외를 발생시킨다.
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("이미 대출중인 책입니다.");
        }

        // 4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        // 5-1. UserLoanRepository를 통해 UserLoanHistory DB에 저장
//        userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));

        // 5-2. 유저를 통해 UserLoanHistory DB에 저장
        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        // 1. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 유저 객체를 통해 대출중인 책 반납
        user.returnBook(request.getBookName()); // 영속성 컨텍스트에 의해 DB와 자동으로 동기화

//        // 2. 유저 정보와 책 정보를 기반으로 도서대출기록을 가저온다.
//        UserLoanHistory userLoanHistory = userLoanHistoryRepository
//                .findByUserIdAndBookNameAndIsReturn(user.getId(), request.getBookName(), false)
//                .orElseThrow(IllegalArgumentException::new);
//
//        // 3. 도서 대출 기록 isReturn필드를 업데이트한다.
//        userLoanHistory.doReturn();
//        userLoanHistoryRepository.save(userLoanHistory); // 영속성 컨텍스트에 의해 자동으로 처리됨
    }
}

package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.IllegalArgumentException

@SpringBootTest

class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록 정상 동작")
    fun saveBookTest() {
        // given
        val request = BookRequest("이상한 나라의 앨리스")

        // when
        bookService.saveBook(request)

        // then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("이상한 나라의 앨리스")
    }

    @Test
    @DisplayName("책 대출 정상 동작")
    fun loanBookTest() {
        // given
        bookRepository.save(Book("이상한 나라의 앨리스"))
        val savedUser = userRepository.save(User("최유나", null))
        val bookLoanRequest = BookLoanRequest("최유나", "이상한 나라의 앨리스")

        // when
        bookService.loanBook(bookLoanRequest)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("이상한 나라의 앨리스")
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].isReturn).isFalse()
    }

    @Test
    @DisplayName("책이 이미 대출중이라면, 신규 대출 실패")
    fun loanBookFailTest() {
        // given
        val savedUser = userRepository.save(User("최유나", null))
        bookRepository.save(Book("이상한 나라의 앨리스"))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "이상한 나라의 앨리스", false))
        val request = BookLoanRequest("최유나", "이상한 나라의 앨리스")

        // when & then
        assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.apply {
            assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
        }
    }

    @Test
    @DisplayName("책 반납 정상 동작")
    fun returnBookTest() {
        // given
        val savedUser = userRepository.save(User("최유나", null))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "이상한 나라의 앨리스", false))
        val request = BookReturnRequest("최유나", "이상한 나라의 앨리스")

        // when
        bookService.returnBook(request)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].isReturn).isTrue()
    }
}
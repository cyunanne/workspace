package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private long userId;
    private String bookName;
    private boolean isReturn; // 테이블의 tinyint(1)에 잘 매핑된다.

    public UserLoanHistory() {}

    public UserLoanHistory(User user, Book book) {
        userId = user.getId();
        bookName = book.getName();
        isReturn = false;
    }

    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = false;
    }
}

package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne // [N:1관계] UserLoanHistory N : User 1
    private User user;
    private String bookName;
    private boolean isReturn; // 테이블의 tinyint(1)에 잘 매핑된다.

    public UserLoanHistory() {}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public UserLoanHistory(User user, Book book) {
        this.user = user;
        this.bookName = book.getName();
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }
}

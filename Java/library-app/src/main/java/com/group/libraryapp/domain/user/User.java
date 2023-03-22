package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 25)
    private String name; // 필수입력
    private Integer age;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // 연관관계의 주인(두 데이터를 연결해주는 객체의 필드) 설정
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User() {}

    public User(String name, Integer age) {
        if( name == null || name.isBlank() ) { // 이름 필수입력
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        // userLoanHistories에 설정된 cascade 옵션 때문에 아래 코드가 실행되면 새로 생긴 userLoanHistory 객체가 DB에 저장됨
        userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}

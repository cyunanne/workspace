package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false) // length 기본값 255이면 생략할 수 있다. 테이블 컬럼명 = 클래스 필드명 이면 생략할 수 있다.
    private String name;

    protected Book() {} // JPA를 사용하려면 기본생성자가 반드시 필요하다.

    public Book(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
    }
}

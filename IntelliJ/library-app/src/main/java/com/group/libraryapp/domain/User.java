package com.group.libraryapp.domain;

public class User {

    private String name; // 필수입력
    private Integer age;

    public User(String name, Integer age) {
        if( name == null || name.isBlank() ) { // 이름 필수입력
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }
}

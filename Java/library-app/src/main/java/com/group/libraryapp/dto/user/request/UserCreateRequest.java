package com.group.libraryapp.dto.user.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {

    private String name; // 필수입력
    private Integer age; // null을 표현할 수 있는 자료형 (필수입력이 아니라서 입력값이 없을 수도 있음)

    public UserCreateRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

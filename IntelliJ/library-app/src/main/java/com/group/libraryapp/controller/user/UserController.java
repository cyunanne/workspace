package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // API 진입지점으로 설정
public class UserController {

    private final List<User> users = new ArrayList<>();

    @PostMapping("/user") // POST /user 요청이 왔을 때 아래 함수가 실행됨
    public void saveUser(@RequestBody UserCreateRequest request) {
        users.add(new User(request.getName(), request.getAge()));
    }

    @GetMapping("/user") // GET /user 요청이 왔을 때 아래 메소드 실행
    public List<UserResponse> getUsers() {
        List<UserResponse> responses = new ArrayList<>();
        for(int i=0; i<users.size(); i++) {
            responses.add(new UserResponse(i+1, users.get(i)));
        }
        return responses;
    }
}

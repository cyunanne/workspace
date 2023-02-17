package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController // API 진입지점으로 설정
public class UserController {

    // 유저 정보 저장방법 1 : 메모리 저장소 생성
//    private final List<User> users = new ArrayList<>();

    // 유저 정보 저장방법 2 : jdbc를 이용해 DB에 접근
    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user") // POST /user 요청이 왔을 때 아래 함수가 실행됨
    public void saveUser(@RequestBody UserCreateRequest request) {
        // 유저 정보 저장방법 1 : 메모리에 유저 정보 저장 -> 프로그램 종료 시 데이터 사라짐
//        users.add(new User(request.getName(), request.getAge()));

        // 유저 정보 저장방법 2 : DB에 SQL문 전달
        String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user") // GET /user 요청이 왔을 때 아래 메소드 실행
    public List<UserResponse> getUsers() {
        // 메모리에 저장된 유저 정보 반환 : 메모리에 저장된 유저 정보에 ID를 발급하여 반환
        /*List<UserResponse> responses = new ArrayList<>();
        for(int i=0; i<users.size(); i++) {
            responses.add(new UserResponse(i+1, users.get(i)));
        }
        return responses;*/

        // DB에 저장된 유저 정보 반환
        String sql = "SELECT * from user";
        // 1) RowMapper 내부함수 전체 구현
        /*return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });*/

        // 2) 람다식 이용
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }
}

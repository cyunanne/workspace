package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    /**
     * jdbctemplate 객체를 사용하는 것은 UserRepository뿐이지만, Spring은 Controller 클래스에 최초로 객체를 전달하기 때문에
     * 생성자를 통해 Controller -> Servcie -> Repository로 객체를 넘겨받아야 한다.
     * */
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist(/*JdbcTemplate jdbcTemplate, */long id) {
        String checksql = "SELECT * FROM user WHERE id = ?";
        // jdbcTemplate.query(sql, RowMapper 구현 익명클래스)는 List를 반환한다.
        return jdbcTemplate.query(checksql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public boolean isUserNotExist(String name) {
        String checksql = "SELECT * FROM user WHERE name = ?";
        // jdbcTemplate.query(sql, RowMapper 구현 익명클래스)는 List를 반환한다.
        return jdbcTemplate.query(checksql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void saveUser(String name, int age) {
        String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUserResponses() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    public void updateUserName(/*JdbcTemplate jdbcTemplate, */String name, long id) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public void deleteUserByName(String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }
}

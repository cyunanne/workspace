package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * jdbc를 활용해서 DB에 접근하는 방식으로 구현
 */
@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(/*JdbcTemplate jdbcTemplate*/UserJdbcRepository userJdbcRepository) {
//        this.userRepository = new UserRepository(/*jdbcTemplate*/);
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUserResponses();
    }

    public void updateUser(/*JdbcTemplate jdbcTemplate, */UserUpdateRequest request) {
        if (userJdbcRepository.isUserNotExist(/*jdbcTemplate, */request.getId())) { // 리스트가 비어있으면
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUserName(/*jdbcTemplate, */request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if(userJdbcRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }
        userJdbcRepository.deleteUserByName(name);
    }
}

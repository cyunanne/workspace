package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge())); // 내장함수 save 활용
//        User user = userRepository.save(new User(request.getName(), request.getAge())); // 내장함수 save 활용
//        System.out.println(user.getId()); // 생성된 객체를 반환하므로 이때 부여된 id를 확인할 수 있다.
     }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream() // findAll(): 모든 데이터 반환
                //.map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .map(UserResponse::new).collect(Collectors.toList()); // 람다 생성자 참조 표현식
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
//        userRepository.save(user); // 영속성 컨텍스트에의해 자동으로 실행됨
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name);
        if( user == null ) {
            throw new IllegalArgumentException();
        }
        userRepository.delete(user);
    }
}

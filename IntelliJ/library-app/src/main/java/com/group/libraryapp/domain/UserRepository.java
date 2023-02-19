package com.group.libraryapp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name); // 함수 이름만 작성하면 JPA가 알아서 SQL을 작성해준다.
}

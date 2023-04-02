package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
) {

    @AfterEach
    fun clean() {
        // 각각의 테스트가 독립적으로 수행될 수 있도록
        // 테스트가 종료될 때 마다 공유자원인 DB의 데이터를 모두 삭제
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장 정상 동작")
    fun saveUserTest() {
        // given
        val request = UserCreateRequest("최유나", null)

        // when
        userService.saveUser(request)

        // then
        val results = userRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("최유나")
        assertThat(results[0].age).isNull()
    }

    @Test
    @DisplayName("유저 조회 정상 동작")
    fun getUsersTest() {
        // given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", null)
        ))

        // when
        val results = userService.getUsers()

        // then
        assertThat(results).hasSize(2) // [UserResponse, UserResponse]
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A", "B") // ["A", "B"]
        assertThat(results).extracting("age").containsExactlyInAnyOrder(20, null) // [20, null]
    }

    @Test
    @DisplayName("유저 업데이트 정상 동작")
    fun updateUserTest() {
        // given
        val savedUser = userRepository.save(User("A", null))
        val request = UserUpdateRequest(savedUser.id, "B")

        // when
        userService.updateUserName(request)

        // then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제 정상 동작")
    fun deleteUserTest() {
        // given
        val savedUser = userRepository.save(User("A", null))

        // when
        userService.deleteUser("A")

        // then
        val results = userRepository.findAll()
        assertThat(results).isEmpty()
    }
}
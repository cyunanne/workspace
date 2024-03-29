/**
 * @Package com.group.libraryapp.controller.user.UserController
 * @Date 2023.2.17.Fri
 */
package regacy.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // API 진입지점으로 설정
public class UserController {

    // 유저 정보 저장방법 1 : 메모리 저장소 생성
//    private final List<User> users = new ArrayList<>();

    // 유저 정보 저장방법 2 : jdbc를 이용해 DB에 접근
//    private final JdbcTemplate jdbcTemplate;
    private final UserServiceV1 userServiceV1;

    /** UserConstroller 클래스가 생성될 때 생성자에 JdbcTemplate 객체가 전달된다.
     * 이 과정은 @RestController이 표시된 클래스에서 Spring에 의해 자동으로 수행된다.
     */
    /*public UserController(JdbcTemplate jdbcTemplate) {
        // this.jdbcTemplate = jdbcTemplate;
        // this.userService = new UserService(jdbcTemplate);
    }*/

    public UserController(UserServiceV1 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }

    @PostMapping("/user") // POST /user 요청이 왔을 때 아래 함수가 실행됨
    public void saveUser(@RequestBody UserCreateRequest request) {
        // 유저 정보 저장방법 1 : 메모리에 유저 정보 저장 -> 프로그램 종료 시 데이터 사라짐
//        users.add(new User(request.getName(), request.getAge()));

        // 유저 정보 저장방법 2 : DB에 SQL문 전달
//        String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
//        jdbcTemplate.update(sql, request.getName(), request.getAge());

        // 유저 정보 저장방법 3 : Service 클래스에 요청
        userServiceV1.saveUser(request);
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
//        String sql = "SELECT * from user";
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
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            long id = rs.getLong("id");
//            String name = rs.getString("name");
//            int age = rs.getInt("age");
//            return new UserResponse(id, name, age);
//        });

        // 서비스 클래스에 요청하여 유저 정보 반환
        return userServiceV1.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        // ---------> service 및 repository 클래스로 코드 이동
        /*String checksql = "SELECT * FROM user WHERE id = ?";
        // jdbcTemplate.query(sql, RowMapper 구현 익명클래스)는 List를 반환한다.
        if (jdbcTemplate.query(checksql, (rs, rowNum) -> 0, request.getId()).isEmpty()) { // 리스트가 비어있으면
            throw new IllegalArgumentException();
        }
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());*/

        // 서비스 클래스에 요청하여 유저 정보 업데이트
        userServiceV1.updateUser(/*jdbcTemplate, */request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        /*String checksql = "SELECT * FROM user WHERE name = ?";
        if (jdbcTemplate.query(checksql, (rs, rowNum) -> 0, name).isEmpty()) {
            throw new IllegalArgumentException();
        }
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);*/

        // 서비스 클래스에 요청하여 유저 정보 삭제
        userServiceV1.deleteUser(name);
    }
}

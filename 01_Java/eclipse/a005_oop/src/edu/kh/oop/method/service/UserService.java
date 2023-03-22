package edu.kh.oop.method.service;

import edu.kh.oop.method.dto.User;
import edu.kh.oop.method.view.UserView;

public class UserService {

    // 1. 회원 가입
    public User signUp(String userId, String userPw, String userPwConfirm, String userName, char userGender) {

        User user = null;
        // 전달받은 값 중 비밀번호와 비밀번호확인 값이 일치할 경우
        if (userPw.equals(userPwConfirm)) {
            // User 객체를 생성해서 참조값 할당
            user = new User(userId, userPw, userName, userGender);
        }
        // 비밀번호가 일치할 경우 참조값, 일치하지 않을 경우 null 반환
        return user;
    }

    // 2. 로그인
    public void login(String userId, String userPw, User signUpUser) {

        boolean idCheck = userId.equals(signUpUser.getUserId()); // ID 일치 여부 검사
        boolean pwCheck = userPw.equals(signUpUser.getUserPw()); // PW 일치 여부 검사
        if (idCheck && pwCheck) {
            UserView.loginUser = signUpUser;
        }
    }

    // 5. 회원 정보 수정
    public boolean userUpdate(String userName, char userGender, String userPw) {

        // 비밀번호 일치여부 확인
        if (!UserView.loginUser.getUserPw().equals(userPw)) { // 논리부정 연산자(!) 사용
            return false;
        }

        // 비밀번호가 일치할 때
        UserView.loginUser.setUserName(userName);
        UserView.loginUser.setUserGender(userGender);

        return true;
    }
}

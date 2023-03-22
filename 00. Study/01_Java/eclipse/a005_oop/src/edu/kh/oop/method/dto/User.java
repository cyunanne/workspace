package edu.kh.oop.method.dto;

/**
 * DTO(Data Transfer Object) : 값 전달용 객체
 * */
public class User {

    // private : 외부 접근 차단(캡슐화 원칙 적용)
    private String userId;
    private String userPw;
    private String userName;
    private char userGender;

    public User() {}

    public User(String userId, String userPw, String userName, char userGender) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userGender = userGender;
    }

    // 객체가 가지고 있는 모든 필드값을 하나의 문자열로 표기하여 반환
    public String toString() {
        return String.format("%s / %s / %s / %c", userId, userPw, userName, userGender);
    }

    /**
     * Getter/Setter 작성
     * : 캡슐화 원칙에 의해 직접 접근이 차단된 필드와 데이터 교환을 할 수 있는 기능(간접 접근 방법)
     * */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getUserGender() {
        return userGender;
    }

    public void setUserGender(char userGender) {
        this.userGender = userGender;
    }
}

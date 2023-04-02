package edu.kh.jdbc.common;

import edu.kh.jdbc.member.model.dto.Member;

// * Session 클래스는 View 에서만 사용 가능하다고 약속 *
public class Session {

    // loginMember == null : 로그아웃 상태
    // loginMember != null : 로그인 상태
    // cf. 로그인 : DB에 저장된 회원 정보를 가져오는 것
    //     -> 로그아웃 전까지 회원정보가 유지되어야 한다.
    public static Member loginMember = null;
}

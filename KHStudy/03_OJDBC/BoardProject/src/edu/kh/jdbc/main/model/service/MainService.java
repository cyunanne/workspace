package edu.kh.jdbc.main.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.model.dto.Member;

import java.sql.Connection;
import java.sql.SQLException;

public class MainService {

    private MainDAO dao = new MainDAO();

    public MainService() {}

    /**
     * 로그인 서비스
     * @param memberId
     * @param memberPw
     * @return member / null
     * @throws Exception
     */
    public Member login(String memberId, String memberPw) throws Exception {
        // 1. 커넥션 생성
        Connection conn = getConnection();
        // 2. DAO 호출
        Member member = dao.login(conn, memberId, memberPw);
        // 3. Connection 반환
        close(conn);
        // 4. 결과 반환
        return member;
    }

    /**
     * ID 중복 체크 서비스
     * @param memberId
     * @return ID가 중복일 경우 1, 아닐 경우 0
     * @throws Exception
     */
    public int idDuplicationCheck(String memberId) throws Exception {
        Connection conn = getConnection();
        int result = dao.idDuplicationCheck(conn, memberId);
        close(conn);
        return result;
    }

    /**
     * 회원 가입 서비스
     * @param member
     * @return result
     * @throws Exception
     */
    public int signUp(Member member) throws Exception {
        Connection conn = getConnection();
        int result = dao.signUp(conn, member); // INSERT 수행

        // 트랜잭션 처리
        if(result > 0) commit(conn);
        else           rollback(conn);

        close(conn);
        return result;
    }
}
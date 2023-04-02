package edu.kh.jdbc.member.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

import java.sql.Connection;
import java.util.List;
import java.util.Random;

public class MemberService {

    private MemberDAO dao = new MemberDAO();

    /**
     * 회원 목록 조회 서비스
     * @return memberList
     * @throws Exception
     */
    public List<Member> selectMemberList() throws Exception {
        Connection conn = getConnection();
        List<Member> memberList = dao.selectMemberList(conn);
        close(conn);
        return memberList;
    }

    /**
     * 회원 정보 수정 서비스
     * @param memberName
     * @param memberGender
     * @param memberNo
     * @return result
     * @throws Exception
     */
	public int updateMember(String memberName, String memberGender, int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, memberName, memberGender, memberNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 비밀번호 변경 서비스
	 * @param curPassword
	 * @param newPassword
	 * @param memberNo
	 * @return result;
	 * @throws Exception
	 */
	public int updatePassword(String curPassword, String newPassword, int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.updatePassword(conn, curPassword, newPassword, memberNo);
		if(result > 0) commit(conn);
		else 	       rollback(conn);
		close(conn);
		return result;
	}
	
	/**
	 * 숫자 6자리 보안코드 생성 서비스
	 * @return code
	 */
	public String createSecurityCode() {
		StringBuffer code = new StringBuffer();
		
		Random rand = new Random(); // 난수생성객체
		for(int i=0; i<6; i++) {
			int x = rand.nextInt(10); // 0~9 정수 반환
			code.append(x); // StringBuffer 마지막에 이어붙이기
		}
		
		return code.toString(); // StringBuffer to String
	}

	/**
	 * 회원 탈퇴 서비스
	 * @param memberPw
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int unregistMember(String memberPw, int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.unregistMember(conn, memberPw, memberNo);
		if(result > 0) commit(conn);
		else 	       rollback(conn);
		close(conn);
		return result;
	}
}

package edu.kh.jdbc.main.model.dao;

import edu.kh.jdbc.member.model.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MainDAO {

    private Statement stmt; // SQL 수행, 결과 반환
    private PreparedStatement pstmt; // placeholder 포함 SQL 작성, 수행, 결과 반환
    private ResultSet rs; // save the result of SELECT

    // Properties
    // - Map<String, String>
    // - XML 파일에 대한 입출력 메서드 제공
    private Properties prop;

    public MainDAO() {
        // XML 파일을 읽어와 Properties 객체에 K:V 형식으로 내용 저장
        // V prop.getProperty(K) 로 사용
        try {
              prop = new Properties();
              prop.loadFromXML(new FileInputStream("main-sql.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 아이디, 비밀번호 일치 회원 조회
     * @param conn
     * @param memberId
     * @param memberPw
     * @return memeber / null
     * @throws Exception
     */
    public Member login(Connection conn, String memberId, String memberPw) throws Exception {
        // 1. 결과 저장용 변수 선언 또는 객체 생성
        Member member = null;

        try {
            // 2. SQL 작성 후 수행
            String sql = prop.getProperty("login");
            // PreparedStatement 객체를 생성하고 SQL을 담아둠
            pstmt = conn.prepareStatement(sql);
            // placeholder에 알맞은 값 대입
            pstmt.setString(1, memberId);
            pstmt.setString(2, memberPw);
            // select 수행 후 결과 반환
            rs = pstmt.executeQuery();

            // 3. 조회 결과 1행씩 접근해서 값 얻어오기
            if(rs.next()) {
                int memberNo = rs.getInt("MEMBER_NO");
                String memberName = rs.getString("MEMBER_NM");
                String memberGender = rs.getString("MEMBER_GENDER"); // DB:CHAR(1) -> Java:String
                String enrollDate = rs.getString("ENROLL_DT");

                member = new Member();
                member.setMemberNo(memberNo);
                member.setMemberId(memberId);
                member.setMemberName(memberName);
                member.setMemberGender(memberGender);
                member.setEnrollDate(enrollDate);
            }
        } finally {
            // 4. 사용한 JDBC 객체 자원 반환
            close(rs);
            close(stmt);
        }
        // 5. 결과 반환
        return member;
    }

    /**
     * ID 중복 검사
     * @param conn
     * @param memberId
     * @return 중복 ID가 존재할 경우 1, 아닐 경우 0
     * @throws Exception
     */
    public int idDuplicationCheck(Connection conn, String memberId) throws Exception{
        int result = 0;

        try {
            String sql = prop.getProperty("idDuplicationCheck");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                result = rs.getInt(1);
            }
        } finally {
            close(rs);
            close(pstmt);
        }
        return result;
    }

    /**
     * 회원 가입
     * @param conn
     * @param member
     * @return
     * @throws Exception
     */
    public int signUp(Connection conn, Member member) throws Exception {
        int result = 0;
        try {
            String sql = prop.getProperty("signUp");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getMemberPw());
            pstmt.setString(3, member.getMemberName());
            pstmt.setString(4, member.getMemberGender());
            result = pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
        return result;
    }
}
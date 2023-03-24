package edu.kh.jdbc.member.model.dao;

import edu.kh.jdbc.member.model.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MemberDAO {

    // JDBC 객체 참조 변수
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // XML에 작성된 SQL을 읽어와 저장할 객체 참조 변수
    private Properties prop;

    public MemberDAO() {
        try {
            prop = new Properties();
            prop.loadFromXML(new FileInputStream("member-sql.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 회원 목록 조회 SQL 수행
     * @param conn
     * @return memberList
     * @throws Exception
     */
    public List<Member> selectMemberList(Connection conn) throws Exception {
        List<Member> memberList = new ArrayList<>();
        try {
            String sql = prop.getProperty("selectMemberList");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String memberId = rs.getString("MEMBER_ID");
                String memberName = rs.getString("MEMBER_NM");
                String memberGender = rs.getString("MEMBER_GENDER");

                Member member = new Member();
                member.setMemberId(memberId);
                member.setMemberName(memberName);
                member.setMemberGender(memberGender);

                memberList.add(member);
            }
        } finally {
            close(rs);
            close(stmt);
        }
        return memberList;
    }
}

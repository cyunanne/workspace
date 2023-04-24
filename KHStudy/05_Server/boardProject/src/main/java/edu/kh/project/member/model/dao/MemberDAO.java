package edu.kh.project.member.model.dao;

import static edu.kh.project.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.project.member.model.dto.Member;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class.getResource("/edu/kh/project/sql/member-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 로그인 DAO
	 * @param conn
	 * @param inputEmail
	 * @param inputPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, String inputEmail, String inputPw) throws Exception {
		Member loginMember = null;
		String sql = prop.getProperty("login");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, inputPw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				loginMember.setMemberTel(rs.getString("MEMBER_TEL"));
				loginMember.setMemberAddress(rs.getString("MEMBER_ADDR"));
				loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
				loginMember.setAuthority(rs.getInt("AUTHORITY"));
				loginMember.setEnrollDate(rs.getString("ENROLL_DATE"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginMember;
	}
}

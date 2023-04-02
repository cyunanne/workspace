package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Comment;

public class CommentDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public CommentDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("comment-sql.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 댓글 목록 조회 SQL 실행
	 * @param conn
	 * @param input
	 * @return commentList
	 * @throws Exception
	 */
	public List<Comment> selectCommentList(Connection conn, int input) throws Exception {
		List<Comment> commentList = new ArrayList<>();
		try {
			String sql = prop.getProperty("selectCommentList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Comment c = new Comment();
				c.setCommentNo(rs.getInt(1));
				c.setCommentContent(rs.getString(2));
				c.setMemberNo(rs.getInt(3));
				c.setMemberName(rs.getString(4));
				c.setCreateDate(rs.getString(5));
				commentList.add(c);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return commentList;
	}

	/**
	 * 댓글 삽입 SQL 실행
	 * @param conn
	 * @param memberNo
	 * @param boardNo
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Connection conn, int memberNo, int boardNo, String content) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("insertComment");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, boardNo);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 댓글 조회(1개) SQL 실행
	 * @param conn
	 * @param commentNo
	 * @return comment
	 * @throws Exception
	 */
	public Comment selectComment(Connection conn, int commentNo) throws Exception {
		Comment comment = null;
		try {
			String sql = prop.getProperty("selectComment");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				comment = new Comment();
				comment.setMemberNo(rs.getInt(1));
				comment.setBoardNo(rs.getInt(2));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return comment;
	}

	/**
	 * 댓글 수정 SQL 실행
	 * @param conn
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Connection conn, int commentNo, String content) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("updateComment");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, commentNo);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * 댓글 삭제 SQL 실행
	 * @param conn
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int deleteComment(Connection conn, int commentNo) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("deleteComment");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}
}

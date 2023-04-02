package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.dto.Comment;

import java.sql.Connection;

public class CommentService {

	CommentDAO dao = new CommentDAO();
	
	/**
	 * 댓글 작성 서비스
	 * @param memberNo
	 * @param boardNo
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int insertComment(int memberNo, int boardNo, String content) throws Exception {
		Connection conn = getConnection();
		int result = dao.insertComment(conn, memberNo, boardNo, content);
		if(result > 0) commit(conn);
		else		   rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 댓글 조회(1개) 서비스
	 * @param commentNo
	 * @return comment
	 * @throws Exception
	 */
	public Comment selectComment(int commentNo) throws Exception {
		Connection conn = getConnection();
		Comment comment = dao.selectComment(conn, commentNo);
		close(conn);
		return comment;
	}

	/**
	 * 댓글 수정 서비스
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(int commentNo, String content) throws Exception {
		Connection conn = getConnection();
		int result = dao.updateComment(conn, commentNo, content);
		if(result > 0) commit(conn);
		else		   rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 댓글 삭제 서비스
	 * @param commentNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteComment(int commentNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.deleteComment(conn, commentNo);
		if(result > 0) commit(conn);
		else		   rollback(conn);
		close(conn);
		return result;
	}
}
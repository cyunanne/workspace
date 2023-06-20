package edu.kh.project.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.board.model.dto.Comment;

@Mapper
public interface CommentMapper {
	
	/**
	 * 댓글 목록 조회
	 * @param boardNo
	 * @return cList
	 */
	List<Comment> select(int boardNo);
	
	/**
	 * 댓글 삽입
	 * @param comment
	 * @return result
	 */
	int insert(Comment comment);
	
	/**
	 * 댓글 삭제
	 * @param commentNo
	 * @return result
	 */
	int delete(int commentNo);

	/**
	 * 댓글 수정
	 * @param comment
	 * @return result
	 */
	int update(Comment comment);

}

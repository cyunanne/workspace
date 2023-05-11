package edu.kh.project.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImage;

@Repository
public class BoardDAO2 {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int boardInsert(Board board) {
		int result = sqlSession.insert("boardMapper.boardInsert", board);
		// => sql 수행 후 매개변수 board.boardNo가 존재 O
		
		// 삽입 성공 시
		if(result > 0) result = board.getBoardNo();
		
		return result; // 삽입 성공 시 bordNo, 실패 시 0 반환
	}

	/**
	 * 이미지 리스트 삽입
	 * @param uploadList
	 * @return result
	 */
	public int insertImageList(List<BoardImage> uploadList) {
		return sqlSession.insert("boardMapper.insertImageList", uploadList);
	}

}

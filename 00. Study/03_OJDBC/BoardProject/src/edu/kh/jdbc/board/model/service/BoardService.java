package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dto.Board;

import java.sql.Connection;
import java.util.List;

// 데이터 가공, 트랜잭션 처리
public class BoardService {
    private BoardDAO dao = new BoardDAO();

    /**
     * 게시글 목록 조회 서비스
     * @return boardList
     * @throws Exception
     */
    public List<Board> selectAllBoard() throws Exception {
        Connection conn = getConnection();
        List<Board> boardList = dao.selectAllBoard(conn);
        close(conn);
        return boardList;
    }

    public Board selectBoard(int input, int memberNo) throws Exception {
        Connection conn = getConnection();
        Board board = dao.selectBoard(conn, input);
        // 게시글이 조회된 경우
        if( board != null ) {
            // 조회수 증가 DAO 메서드 호출(작성자가 아닌 경우만)
            if( board.getMemberNo() != memberNo ) {
               int result = dao.updateReadCount(conn, input);
               if(result > 0) {
                   commit(conn);
                   // 조회 결과인 board의 조회수 1 증가
                   board.setReadCount(board.getReadCount() + 1);
               } else {
                   rollback(conn);
               }
            }
        }
        close(conn);
        return board;
    }

    /**
     * 게시글 수정 Service
     * @param boardNo
     * @param boardTitle
     * @param boardContent
     * @return result
     * @throws Exception
     */
    public int updateBoard(int boardNo, String boardTitle, String boardContent) throws Exception {
        Connection conn = getConnection();
        int result  = dao.updateBoard(conn, boardNo, boardTitle, boardContent);
        if(result > 0) commit(conn);
        else           rollback(conn);
        close(conn);
        return result;
    }

    /**
     * 게시글 삭제 서비스
     * @param boardNo
     * @return result
     * @throws Exception
     */
    public int deleteBoard(int boardNo) throws Exception {
        Connection conn = getConnection();
        int result = dao.deleteBoard(conn, boardNo);
        if(result > 0) commit(conn);
        else           rollback(conn);
        close(conn);
        return result;
    }

    /**
     * 게시글 작성 서비스
     * @param title
     * @param contents
     * @param memberNo
     * @return result
     * @throws Exception
     */
    public int insertBoard(String title, String contents, int memberNo) throws Exception {
        Connection conn = getConnection();
        // 다음 게시글 번호 생성 -> 4
        int boardNo = dao.nextBoardNumber(conn);
        // SQL에 제목, 내용, 회원번호 + 다음 게시글번호(4) 전달
        int result = dao.insertBoard(conn, title, contents, memberNo, boardNo);
        if(result > 0) {
            commit(conn);
            result = boardNo;
        } else {
            rollback(conn);
        }
        close(conn);
        // 삽입 성공 시 다음 게시글 번호, 실패 시 0 반환
        return result;
    }
}

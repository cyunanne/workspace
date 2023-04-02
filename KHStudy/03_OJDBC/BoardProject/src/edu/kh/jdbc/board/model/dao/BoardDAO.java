package edu.kh.jdbc.board.model.dao;

import edu.kh.jdbc.board.model.dto.Board;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardDAO {
    // JDBC 객체 참조 변수
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // XML 파일에서 sql을 읽어오기 위한 변수
    private Properties prop;

    public BoardDAO() {
        try {
            prop = new Properties();
            prop.loadFromXML(new FileInputStream("board-sql.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 게시글 목록 조회 SQL 수행
     * @param conn
     * @return boardList
     * @throws Exception
     */
    public List<Board> selectAllBoard(Connection conn) throws Exception {
        List<Board> boardList = new ArrayList<>();
        try {
            String sql = prop.getProperty("selectAllBoard");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int boardNo = rs.getInt("BOARD_NO");
                String boardTitle = rs.getString("BOARD_TITLE");
                String memberName = rs.getString("MEMBER_NM");
                int readCount = rs.getInt("READ_COUNT");
                String createDate = rs.getString("CREATE_DT");
                int commentCount = rs.getInt("COMMENT_COUNT");

                Board board = new Board();
                board.setBoardNo(boardNo);
                board.setBoardTitle(boardTitle);
                board.setMemberName(memberName);
                board.setReadCount(readCount);
                board.setCreateDate(createDate);
                board.setCommentCount(commentCount);

                boardList.add(board);
            }
        } finally {
            close(rs);
            close(stmt);
        }
        return boardList;
    }

    public Board selectBoard(Connection conn, int input) throws Exception {
        Board board = null;
        try {
            String sql = prop.getProperty("selectBoard");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, input);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                int boardNo = rs.getInt("BOARD_NO");
                String boardTitle = rs.getString("BOARD_TITLE");
                String boardContent = rs.getString("BOARD_CONTENT");
                int memberNo = rs.getInt("MEMBER_NO");
                String memberName = rs.getString("MEMBER_NM");
                int readCount = rs.getInt("READ_COUNT");
                String createDate = rs.getString("CREATE_DT");

                board = new Board();
                board.setBoardNo(boardNo);
                board.setBoardTitle(boardTitle);
                board.setBoardContent(boardContent);
                board.setMemberNo(memberNo);
                board.setMemberName(memberName);
                board.setReadCount(readCount);
                board.setCreateDate(createDate);
            }
        } finally {
            close(rs);
            close(pstmt);
        }
        return board;
    }

    public int updateReadCount(Connection conn, int input) throws Exception {
        int result = 0;
        try {
            String sql = prop.getProperty("updateReadCount");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,input);
            result = pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
        return result;
    }

    /**
     * 게시글 수정
     * @param conn
     * @param boardNo
     * @param boardTitle
     * @param boardContent
     * @return result
     * @throws Exception
     */
    public int updateBoard(Connection conn, int boardNo, String boardTitle, String boardContent) throws Exception {
        int result = 0;
        try {
            String sql = prop.getProperty("updateBoard");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardTitle);
            pstmt.setString(2, boardContent);
            pstmt.setInt(3, boardNo);
            result = pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
        return result;
    }

    /**
     * 게시글 삭제 SQL 실행
     * @param conn
     * @param boardNo
     * @return result
     * @throws Exception
     */
    public int deleteBoard(Connection conn, int boardNo) throws Exception {
        int result = 0;
        try {
            String sql = prop.getProperty("deleteBoard");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);
            result = pstmt.executeUpdate();
            // executeUpdate() 를 통해 DDL도 수행 가능 -> 결과 -1 반환(성공여부 알 수 없음)
        } finally {
            close(pstmt);
        }
        return result;
    }

    /**
     * 게시글 작성 SQL 실행
     *
     * @param conn
     * @param title
     * @param contents
     * @param memberNo
     * @param boardNo
     * @return result
     * @throws Exception
     */
    public int insertBoard(Connection conn, String title, String contents, int memberNo, int boardNo) throws Exception {
        int result = 0;
        try {
            String sql = prop.getProperty("insertBoard");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);
            pstmt.setString(2, title);
            pstmt.setString(3, contents);
            pstmt.setInt(4, memberNo);
            result = pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int nextBoardNumber(Connection conn) throws Exception {
        int nextBoardNo = 0;
        try {
            String sql = prop.getProperty("nextBoardNumber");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                nextBoardNo = rs.getInt(1);
            }
        } finally {
            close(rs);
            close(stmt);
        }
        return nextBoardNo;
    }
}

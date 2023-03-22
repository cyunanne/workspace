package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBCTemplate : JDBC 관련 작업 수행을 위한 코드를 제공하는 클래스
 *   1) DB 연결을 위한 Connection 생성 구문
 *   2) JDBC 객체 자원 반환 구문(close())
 *   3) commit, rollback 구문
 *   4) auto commit 기능 off
 */
public class JDBCTemplate {

    private static Connection conn = null;

    public static Connection getConnection() {

        try {
            // 커넥션 객체가 없거나 닫혀있는 경우 -> 새로운 연결(커넥션) 생성
            // conn.isClosed() : 커넥션이 close 상태이면 true 반환
            if(conn == null || conn.isClosed()) {
                Properties prop = new Properties(); // Map<String, String>, XML 파일 입출력 특화

                // 스트림을 이용해서 driver.xml 파일을 읽어와 prop에 저장
                prop.loadFromXML(new FileInputStream("driver.xml"));

                // prop에 저장된 값을 변수로 따로 저장
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String pw = prop.getProperty("pw");

                // Oracle JDBC 객체를 매모리에 로드
                Class.forName(driver);

                // DriverManager를 이용해 Connection 생성
                conn = DriverManager.getConnection(url, user, pw);

                /**** 자동커밋 비활성화 *****/
                // -> 개발자가 직접 트랜잭션을 제어하기 위해서
                conn.setAutoCommit(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    /**
     * Connection.close() 메소드
     * @Param : conn
     */
    public static void close(Connection conn) {
        try {
            if(conn != null || !conn.isClosed()) conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Statement.close() 메소드
     * @Param : stmt
     */
    public static void close(Statement stmt) {
        try {
            if(stmt != null || ! stmt.isClosed()) stmt.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ResultSet.close() 메소드
     * @Param : rs
     */
    public static void close(ResultSet rs) {
        try {
            if(rs != null || !rs.isClosed()) rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Transaction Commit 메서드
     * @Param : conn
     */
    public static void commit(Connection conn) {
        try {
            if(conn != null || !conn.isClosed()) conn.commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Transaction Rollback 메서드
     * @Param : conn
     */
    public static void rollback(Connection conn) {
        try {
            if(conn != null || !conn.isClosed()) conn.rollback();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

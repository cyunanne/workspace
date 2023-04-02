package edu.kh.jdbc.run;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;

public class JDBCExample1 {
    // JDBC : 자바에서 DB에 연결(접근)할 수 있게 해주는 코드(class, interface)를 제공해주는 것(java.sql 패키지에서 제공)
    //        => Java Programming API

    /*
     * JDBC를 이용한 애플리케이션 제작 시 필요한 것
     *
     * 1. Java의 JDBC 관련 인터페이스
     * 2. DBMS의 종류(Oracle)
     * 3. DBMS 회사에서 제공하는 Java Application과 DB 연결을 위한 라이브러리(ojdbc11.jar -> OracleDriver.class)
     */

    public static void main(String[] args) {

        /* 1단계 : JDBC 객체 참조 변수 선언 */

        // DB 연결 정보를 담는 객체 -> Connection을 상속 받아 구현한 자식 객체가 대입될 예정
        // DBMS 이름, 타입, IP, PORT, 계정명, 비밀번호 저장(DBeaver의 계정 접속 방법 추가와 유사함
        Connection conn = null;

        // The object used for executing a static SQL statement and returning the results it produces.
        // SQL을 DB에 전달해 수행한 결과(Result Set 또는 DML 성공 행의 갯수)를 반환
        Statement stmt = null;

        // SELECT 질의 성공 시 반환 되는 객체(조회 결과의 집합) -> 테이블 모양
        // ResultSet 객체를 이용해 1행씩 접근, 더 이상 접근할 행이 없을 경우 false 반환
        ResultSet rs = null;

        /* 2단계 : 1단계에서 선언한 참조변수에 알맞은 객체 대입 */
        try {
            // (1) DB 연결에 필요한 Oracle JDBC Driver 객체를 메모리에 로드 => OracleDriver.class 객체 생성

            // Class.forName() : 매개변수로 전달된 경로의 클래스를 이용해 객체 생성
            // => 메모리에 객체가 생성되고 Connection을 이용한 DB 연결 시 이를 자동으로 참조
            //    ClassNotFoundException : 오타 또는 라이브러리 추가 안하면 발생
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // (2) DB 연결 정보를 담은 Connection 객체 생성(DriverManager 이용)

            // DB 연결을 위한 정보를 변수에 저장
            String type = "jdbc:oracle:thin:@"; // 드라이버 종류
            String ip = "115.90.212.22";
            String port = ":9000"; // 컴퓨터 내 응용프로그램 번호
            String dbName = ":XE"; // DB이름(SID)
            String user = "kh_cyn";
            String pw = "oracle_cyn123A";

            // DriverManager 객체
            // : 메모리에 로드된 JDBC드라이버 객체를 이용해 DB에 접근하고, 작성된 정보를 이용해 DB와 연결 가능한 Connection 객체를 반환
            // => SQLException : DB 연결 실패 시 발생
            conn = DriverManager.getConnection(type + ip + port + dbName, user, pw);

            // 정상적으로 작성한 경우 : conn 객체의 주소가 출력
            // 잘못 작성한 경우     : 예외 발생
            //System.out.println(conn);

            // (3) SQL 작성
            // Java에서 작성되는 SQL은 마지막에 세미콜론(;)을 붙이지 않는다.
            String sql = "SELECT EMP_ID, EMP_NAME, SALARY, HIRE_DATE FROM EMPLOYEE";

            // (4) Statement 객체 생성
            // DB에 SQL 전달 후 수행 결과를 반환
            // Connection 내부에 생성되어 DB와 통신하는 역할
            stmt = conn.createStatement();

            // (5) Statement 객체를 통해 SQL(SELECT)을 수행하고 결과(ResultSet)를 반환 받아 저장(to rs)
            // executeQuery() : SELECT문을 수행하고 ResultSet을 반환하는 메서드
            rs = stmt.executeQuery(sql);

            /* 3단계 : SQL 수행 결과로 반환 받은 ResultSet을 첫 번째 행부터 1행씩 접근하여 컬럼 값 출력 */
            while(rs.next()) {
                // rs.next() : ResultSet 객체는 조회 결과를 하나의 테이블로 다루는 것이 아닌 커서(Cursor)를 이용해 1행씩 접근 한다.
                //             커서를 다음 행으로 "이동" 후 행이 존재 하면 true, 없으면 false를 반환 한다.

                // 현재 접근한 행의 컬럼 값을 출력
                // rs.get자료형(컬럼명|컬럼순서)
                //       Java     :       DB
                // ---------------:----------------
                //      String    : CHAR, VARCHAR2
                //    int, long   :   NUMBER(정수)
                //  float, double :   NUMBER(실수)
                //  java.sql.Date :      DATE
                String empId = rs.getString("EMP_ID"); // 현재 커서가 접근한 행의 "EMP_ID" 컬럼이 값을 얻어와 String으로 반환
                String empName = rs.getString("EMP_NAME");
                int salary = rs.getInt("SALARY");
                Date hireDate = rs.getDate("HIRE_DATE");
                // java.sql.Date hireDate = rs.getDate("HIRE_DATE"); // 이것도 가넝

                System.out.printf("사번: %s / 이름: %s / 급여: %d / 입사일: %s\n", empId, empName, salary, hireDate);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("지정된 경로에 클래스가 존재하지 않습니다.");
            e.printStackTrace();
        } catch(SQLException e) { // SQLException : JDBC 관련 객체 이용 중 발생되는 예외의 최상위 부모
            e.printStackTrace();
        } finally {
            /* 4단계 : 사용한 JDBC 객체 자원 반환(close()) */
            // close 구문은 객체 생성 역순으로 작성한다.
            // 생성 순서 : conn - stmt - rs
            // close 순서 : rs - stmt - conn
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
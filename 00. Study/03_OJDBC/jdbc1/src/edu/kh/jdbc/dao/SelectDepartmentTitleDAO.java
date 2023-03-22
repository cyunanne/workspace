package edu.kh.jdbc.dao;

import edu.kh.jdbc.dto.Employee1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO(Data Access Object) : 데이터에 접근하기 위한 객체
 * == DB 연결 역할 객체
 */
public class SelectDepartmentTitleDAO {
    /**
     * 부서명으로 사원 조회
     * @param departmentTitle
     * @return List<Employee1>
     */
    public List<Employee1> select(String departmentTitle) {
        List<Employee1> empList = new ArrayList<>();

        // 1단계 : JDBC 객체 참조변수 선언
        Connection conn = null; // DB 연결 정보 저장, Statement 통로
        Statement stmt = null;  // SQL 수행, 결과 반환
        ResultSet rs = null;    // SELECT 수행 결과 저장용 객체

        // 2단계 : 참조변수에 알맞은 값 대입
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // DB 연결 정보
            String type = "jdbc:oracle:thin:@"; // JDBC Driver type
            String ip = "localhost"; //
            String port = ":1521";
            String dbName = ":XE"; // 19c=:ORCL , 18c=:XE
            String user = "yuna";
            String pw = "oracle_cyn123A";

            // DriverManager를 이용해 Connection 생성
            // String url = "jdbc:oracle:thin:@115.90.212.22:9000:XE";
            // conn = DriverManager.getConnection(url, "kh_cyn", "oracle_cyn123A");
            conn = DriverManager.getConnection(type+ip+port+dbName, user, pw);

            // 부서명이 없는 직원 검색
            String condition = "";
            if( departmentTitle.toLowerCase().equals("null") )
                condition = "WHERE DEPT_TITLE IS NULL ";
            else
                condition = "WHERE DEPT_TITLE = '" + departmentTitle + "' ";

            // SQL 작성
            String sql = "SELECT EMP_ID, EMP_NAME, SALARY, DEPT_TITLE "
                       + "FROM EMPLOYEE "
                       + "LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID) "
                       + condition
                      // + "WHERE DEPT_TITLE = '" + departmentTitle + "' "
                       + "ORDER BY EMP_ID";

            stmt = conn.createStatement(); // Statement 객체 생성

            rs = stmt.executeQuery(sql); // SELECT 수행 후 결과 반환

            while(rs.next()) {
                String empId = rs.getString(1); // 조회 결과 컬럼 순서
                String empName = rs.getString(2);
                int salary = rs.getInt(3);
                String deptTitle = rs.getString(4);

                // 조회된 컬럼값을 Employee1 객체에 저장
                empList.add(new Employee1(empId, empName, salary, deptTitle));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return empList;
    }
}

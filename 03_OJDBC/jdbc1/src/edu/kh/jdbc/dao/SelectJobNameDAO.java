package edu.kh.jdbc.dao;

import edu.kh.jdbc.dto.Employee2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectJobNameDAO {

    public List<Employee2> select(String jobName) {
        List<Employee2> empList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            conn = DriverManager.getConnection(url, "yuna", "oracle_cyn123A");
            stmt = conn.createStatement();

            String sql = "SELECT DEPT_TITLE, JOB_NAME, EMP_NAME, EMAIL " +
                    "FROM EMPLOYEE " +
                    "LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID) " +
                    "JOIN JOB USING(JOB_CODE) " +
                    "WHERE JOB_NAME = '" + jobName + "' " +
                    "ORDER BY EMP_NAME";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String deptTitle = rs.getString(1) == null ? "부서없음" : rs.getString(1);
                String job = rs.getString(2);
                String empName = rs.getString(3);
                String email = rs.getString(4);

                empList.add(new Employee2(deptTitle, job, empName, email));
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

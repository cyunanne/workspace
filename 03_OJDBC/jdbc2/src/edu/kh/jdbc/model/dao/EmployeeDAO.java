package edu.kh.jdbc.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.model.dto.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO(Data Access Object) : DB 접근용 객체
public class EmployeeDAO {

    // JDBC 구문이 여러 번 작성 될 예정
    // -> JDBC 객체 참조 변수가 계속 작성 될 예정 => 필드로 작성하여 재사용
    private Statement stmt;
    private ResultSet rs;

    // 외부 변수를 SQL에 삽입할 준비가 된 Statement
    private PreparedStatement pstmt;

    /**
     * 전체 사원 조회 SQL 수행 후 결과 반환 메서드
     * @param conn
     * @return empList
     * @throws SQLException
     */
    public List<Employee> selectAll(Connection conn) throws SQLException {

        // 1. 결과 저장을 위한 변수/객체 준비
        List<Employee> empList = new ArrayList<>();

        try {
            // 2. Statement, ResultSet에 객체 대입
            // 1) SQL 작성
            String sql = "SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '없음') DEPT_TITLE, JOB_NAME, NVL(PHONE, '없음') PHONE "
                    + "FROM EMPLOYEE "
                    + "NATURAL JOIN JOB "
                    + "LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID) "
                    + "ORDER BY JOB_CODE, EMP_ID";

            stmt = conn.createStatement(); // 2) Statement 객체 생성
            rs = stmt.executeQuery(sql); // 3) sql 조회 결과 저장

            // 3. 조회 결과에 1행씩 접근하여 컬럼 값을 얻어와 List에 담기
            while(rs.next()) {
                String empId = rs.getString(1);
                String empName = rs.getString(2);
                String deptTitle = rs.getString(3);
                String jobName = rs.getString(4);
                String phone = rs.getString(5);

                Employee emp = new Employee(Integer.parseInt(empId), empName, phone, deptTitle, jobName);
                empList.add(emp); // 리스트 추가
            }
        // catch문 -> throws 구문으로 예외처리
        } finally {
            // 4. JDBC 객체 자원 반환(단, Connection 빼고)
            close(rs);
            close(stmt);
        }

        // 5. 결과 반환
        return empList;
    }

    /**
     * 사원 1명 조회 SQL 수행 후 결과 반환 메서드
     * @param conn
     * @param input
     * @return emp
     * @throws SQLException
     */
    public Employee selectOne(Connection conn, int input) throws SQLException {

        // 1. 결과 저장을 위한 변수/객체 준비
        // -> 조회 결과가 있을 때 객체 생성
        Employee emp = null;

        try {
            // 2. SQL 작성 후 수행
            String sql = "SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '없음') DEPT_TITLE, JOB_NAME, NVL(PHONE, '없음') PHONE "
                    + "FROM EMPLOYEE "
                    + "NATURAL JOIN JOB "
                    + "LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID) "
                    + "WHERE EMP_ID = " + input;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // 3. 조회 결과가 있는지 확인 후 있으면(1행) Employee 객체 생성 후 emp에 대입
            if(rs.next()) {
                // DB에서 값을 얻어올 때 "숫자" (문자열로 된 숫자) 형태일 경우 getInt()를 작성하면 자동으로 int 형변환 진행
                int empId = rs.getInt(1);
                String empName = rs.getString(2);
                String deptTitle = rs.getString(3);
                String jobName = rs.getString(4);
                String phone = rs.getString(5);

                // 객체를 생성해서 emp에 대입
                emp = new Employee(empId, empName, phone, deptTitle, jobName);
            }
        } finally {
            // 4. JDBC 객체 자원 반환(Connection 제외)
            close(rs);
            close(stmt);
        }

        //5. 결과 반환
        return emp;
    }

    /**
     * 키워드로 이름 검색 SQL 수행 후 결과 반환 메서드
     * @param conn
     * @param input
     * @return empList
     * @throws SQLException
     */
    public List<Employee> selectName(Connection conn, String input) throws SQLException {
        List<Employee> empList = new ArrayList<>();

        try {
            String sql = "SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '없음') DEPT_TITLE, JOB_NAME, NVL(PHONE, '없음') PHONE "
                    + "FROM EMPLOYEE "
                    + "NATURAL JOIN JOB "
                    + "LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID) "
                    + "WHERE EMP_NAME LIKE '%" + input + "%' "
                    + "ORDER BY EMP_ID";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                // DB에서 값을 얻어올 때 "숫자" (문자열로 된 숫자) 형태일 경우 getInt()를 작성하면 자동으로 int 형변환 진행
                int empId = rs.getInt(1);
                String empName = rs.getString(2);
                String deptTitle = rs.getString(3);
                String jobName = rs.getString(4);
                String phone = rs.getString(5);

                // 객체를 생성해서 empList에 대입
                empList.add(new Employee(empId, empName, phone, deptTitle, jobName));
            }
        } finally {
            close(rs);
            close(stmt);
        }

        return empList;
    }

    /**
     * 급여 범위로 사원 조회 SQL 수행 후 결과 반환 메서드
     * @param conn
     * @param min 최소급여
     * @param max 최대급여
     * @return empList
     * @throws SQLException
     */
    public List<Employee> selectSalary(Connection conn, int min, int max) throws SQLException {
        List<Employee> empList = new ArrayList<>();

        try {
            /*
            // 2. SQL 작성 후 Statement 객체를 생성해 SQL 수행
            String sql = "SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY "
                    + "FROM EMPLOYEE "
                    + "NATURAL JOIN JOB "
                    + "WHERE SALARY BETWEEN " + min + " AND " + max
                    + " ORDER BY SALARY DESC";

            stmt = conn.createStatement(); // SQL 수행 결과를 반환 받을 Statement 객체 생성
            rs = stmt.executeQuery(sql); // SQL 수행 결과(ResultSet)를 반환 받아 저장
            */
            //-----------------------------------------------------------------------------
            // **2. PreparedStatement 사용
            String sql = "SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY "
                    + "FROM EMPLOYEE "
                    + "NATURAL JOIN JOB "
                    + "WHERE SALARY BETWEEN ? AND ? " // 값이 동적으로 추가되는 부분을 '?'(placeholder)로 작성
                    + "ORDER BY SALARY DESC";

            // SQL 수행 결과를 반환 받을 PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            // PreparedStatement에 추가된 sql 구문에서 미완성된 부분에 알맞은 값을 추가
            // PreparedStatement.set자료형(순서, 값) : 순서번 째 Placeholder에 값 삽입
            pstmt.setInt(1, min);
            pstmt.setInt(2, max);
            // 준비된 PreparedStatement를 이용해 SQL 수행 후 결과 반환
            // PreparedStatement 객체 생성 시 SQL이 담기기 때문에 SQL 수행 시 SQL 구문 전달 안함
            rs = pstmt.executeQuery();

            while(rs.next()) {
                // DB에서 값을 얻어올 때 "숫자" (문자열로 된 숫자) 형태일 경우 getInt()를 작성하면 자동으로 int 형변환 진행
                int empId = rs.getInt(1);
                String empName = rs.getString(2);
                String jobName = rs.getString(3);
                int salary = rs.getInt(4);

                // 객체를 생성해서 empList에 대입
                // 기본생성자 + setter (반복사용하지 않는 생성자 작성 지양)
                Employee emp = new Employee();
                emp.setEmpId(empId);
                emp.setEmpName(empName);
                emp.setJobName(jobName);
                emp.setSalary(salary);
                empList.add(emp);
            }
        } finally {
            close(rs);
            //close(stmt);
            close(pstmt);
        }

        return empList;
    }

    /**
     * 사원 정보를 삽입하는 SQL 수행 후 결과 행 개수 반환
     * @param conn
     * @param emp
     * @return
     * @throws SQLException
     */
    public int insertEmployee(Connection conn, Employee emp) throws SQLException {

        // DML 수행 시 영향을 끼친 행의 개수가 반환 된다. (삽입/수정/삭제된 행의 개수)
        // -> 행의 갯수는 정수이므로 int 형으로 반환

        // 1. 결과를 저장할 변수/객체 선언
        int result = 0;

        try {
            // 2. PreparedStatement 객체 생성
            // 2.1. SQL 작성
            String sql = "INSERT INTO EMPLOYEE VALUES(SEQ_EMP_ID.NEXTVAL," +
                    "?,?,?,?,?,?,?,?,?,?,SYSDATE,NULL,'N')";
            // 2.2. PreparedStatement 객체 생성 후 placeholder에 값 세팅
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getEmpName());
            pstmt.setString(2, emp.getEmpNo());
            pstmt.setString(3, emp.getEmail());
            pstmt.setString(4, emp.getPhone());
            pstmt.setString(5, emp.getDeptCode());
            pstmt.setString(6, emp.getJobCode());
            pstmt.setString(7, emp.getSalLevel());
            pstmt.setInt(8, emp.getSalary());
            pstmt.setDouble(9, emp.getBonus());
            pstmt.setInt(10, emp.getManagerId());

            // 3. SQL 수행 후 결과 반환 받기
            // executeQuery() : SEELCT 수행 후 ResultSet 반환
            // executeUpdate() : DML 수행 후 결과 행의 개수 반환
            result = pstmt.executeUpdate();

            /* SELECT 와 달리 옮겨 담는 과정 없음 */
        } finally {
            // 4. JDBC 객체 반환
            // Statement 를 close() 하는 메서드 호출 (매개변수에 다형성 UpCasting 적용)
            close(pstmt);
        }
        // 5. 결과 반환
        return result;
    }

    /**
     * 사원 정보를 수정하는 SQL 수행 후 결과 행 개수 반환
     * @param conn
     * @param emp
     * @return
     * @throws SQLException
     */
    public int updateEmployee(Connection conn, Employee emp) throws SQLException {

        // 1. 결과 저장용 변수 선언
        int result = 0;

        try {
            // 2. SQL & PreparedStatement 생성
            // 2.1. SQL 작성
            String sql = "UPDATE EMPLOYEE SET EMAIL=?, PHONE=?, SALARY=? WHERE EMP_ID=?";
            // 2.2. PreparedStatement 생생 후 placeholder에 값 세팅
            // PreparedStatement.set자료형() 으로 placeholer에 세팅할 떄 자료형에 맞는 DB 리터럴 표기법으로 변환되어 세팅된다.
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getEmail());
            pstmt.setString(2, emp.getPhone());
            pstmt.setInt(3, emp.getSalary());
            pstmt.setInt(4, emp.getEmpId());

            // 3. SQL 수행 결과 반환
            result = pstmt.executeUpdate();
        } finally {
            // 4. JDBC 객체 반환
            close(pstmt);
        }

        // 5. 결과 반환
        return result;
    }

    /**
     * 사원 퇴사 처리 SQL 수행 후 결과 행 개수 반환
     * @param conn
     * @param input
     * @return result - SQL 수행 결과 행 갯수
     * @throws SQLException
     */
    public int retireEmployee(Connection conn, int input) throws SQLException {

        int result = 0;

        try {
            String sql = "UPDATE EMPLOYEE SET ENT_YN='Y', ENT_DATE=SYSDATE WHERE EMP_ID=? AND ENT_YN='N'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, input);
            result = pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }

        return result;
    }
}

package edu.kh.jdbc.model.service;

/*
 * Model
 * - 데이터 논리 구조 제어(트랜잭션 제어), 데이터 가공 처리 => 비즈니스 로직
 * - DAO 수행 결과를 View/Controller로 반환
 * => 프로그램(앱)에 포함되어야 할 데이터에 대한 정의...
 */

// JDBCTemplate에 있는 static 메서드를 가져와 자신의 것 처럼 사용
import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.model.dao.EmployeeDAO;
import edu.kh.jdbc.model.dto.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// Service : 비즈니스 로직
// -> 요청에 따른 필요 데이터를 반환 + 트랜잭션 제어 처리(Commit, Rollback)
public class EmployeeService {

    // dao에 여러 SQL을 수행하기 위한 메서드를 각각 작성하여 호출
    private EmployeeDAO dao = new EmployeeDAO();

    /**
     * 전체 사원 정보 반환 서비스
     * @return empList
     * @throws SQLException
     */
    public List<Employee> selectAll() throws SQLException {

        /* DB에서 필요한 데이터를 조회하기 위해 DAO 메서드를 호출 */

        // 1. 커넥션 생성
        // -> Service가 트랜잭션 제어 처리를 위해서는 Connection 객체가 Service에 존재해야 한다.
        //    만약 DAO에서 Connection을 만들고 반환하면 Service쪽에서 Connection을 사용할 수 없게 된다.
        // -> Connection이 서비스에 있다면 다 쓰고 닫아주는 close() 구문이 필요하다.
        // => 서비스에 Connection.close()를 작성해야 한다.
        Connection conn = getConnection();

        // 2. DAO의 메서드를 호출하여 필요한 결과를 DB에서 조회해서 반환 받기
        List<Employee> empList = dao.selectAll(conn);

        // ***** DML 구문인 경우 이 위치에서 Commit/Rollback 구문 작성 *****

        // 3. Connection 반환
        close(conn); // JDBCTemplate에 작성된 메서드 호출

        // 4. 결과 반환
        return empList;
    }

    /**
     * 사원 1명 정보 반환 서비스
     * @param input
     * @return Employee
     * @throws SQLException
     */
    public Employee selectOne(int input) throws SQLException {

        // 1. Connection 생성
        Connection conn = getConnection();
        // 2. DAO 메서드를 호출(커넥션, input)
        Employee emp = dao.selectOne(conn, input);
        // 3. Connection 반환
        close(conn);
        // 4. 결과 반환
        return emp;
    }

    /**
     * 키워드로 이름 검색 서비스
     * @param input
     * @return empList
     * @throws SQLException
     */
    public List<Employee> selectName(String input) throws SQLException {
        Connection conn = getConnection();
        List<Employee> empList = dao.selectName(conn, input);
        close(conn);
        return empList;
    }

    /**
     * 급여 범위로 사원 조회 서비스
     * @param min
     * @param max
     * @return empList
     * @throws SQLException
     */
    public List<Employee> selectSalary(int min, int max) throws SQLException {
        Connection conn = getConnection();
        List<Employee> empList = dao.selectSalary(conn, min, max);
        close(conn);
        return empList;
    }
}
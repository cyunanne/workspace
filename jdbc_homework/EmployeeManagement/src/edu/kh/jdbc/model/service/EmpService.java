package edu.kh.jdbc.model.service;

import edu.kh.jdbc.model.dao.EmpDAO;
import edu.kh.jdbc.model.dto.Emp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class EmpService {

	private EmpDAO dao = new EmpDAO();

	/**
	 * 재직중인 사원 전체 조회
	 * @return
	 * @throws SQLException
	 */
	public List<Emp> selectAll() throws SQLException {
		Connection conn = getConnection();
		List<Emp> empList = dao.selectAll(conn);
		close(conn);
		return empList;
	}

	/**
	 * 퇴직한 사원 전체 조회
	 * @return
	 * @throws SQLException
	 */
	public List<Emp> selectRetiredAll() throws SQLException {
		Connection conn = getConnection();
		List<Emp> empList = dao.selectRetiredAll(conn);
		close(conn);
		return empList;
	}

	/**
	 * 사번이 일치하는 사원 조회
	 * @return
	 * @throws SQLException
	 */
	public Emp selectOneByID(int id) throws SQLException {
		Connection conn = getConnection();
		Emp e = dao.selectOneByID(conn, id);
		close(conn);
		return e;
	}

	/**
	 * 사원 추가
	 * @param e
	 * @return
	 * @throws SQLException
	 */
	public int insertEmp(Emp e) throws SQLException {
		Connection conn = getConnection();
		int result = dao.insertEmp(conn, e);
		if(result >= 1) commit(conn);
		else 		    rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 사원 정보 수정
	 * @param emp
	 * @return
	 * @throws SQLException
	 */
	public int updateById(Emp emp) throws SQLException {
		Connection conn = getConnection();
		int result = dao.updateByID(conn, emp);
		if(result >= 1) commit(conn);
		else 		    rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 사원 삭제
	 * @param id
	 * @return result
	 * @throws SQLException
	 */
	public int deleteById(int id) throws SQLException {
		Connection conn = getConnection();
		int result = dao.deleteById(conn, id);
		if(result >= 1) commit(conn);
		else 		    rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 퇴직 처리
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updateRetireById(int id) throws SQLException {
		Connection conn = getConnection();
		int result = dao.updateRetireById(conn, id);
		if(result >= 1) commit(conn);
		else 		    rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 가장 최근 입사한 사원 5명 조회
	 * @return empList
	 * @throws SQLException
	 */
	public List<Emp> selectLatestEnterTop5() throws SQLException {
		Connection conn = getConnection();
		List<Emp> empList = dao.selectLatestEnterTop5(conn);
		close(conn);
		return empList;
	}

	/**
	 * 부서별 통계 조회
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> departmentStatistics() throws SQLException {
		Connection conn = getConnection();
		List<Map<String, String>> statistics = dao.departmentStatistics(conn);
		close(conn);
		return statistics;
	}
}

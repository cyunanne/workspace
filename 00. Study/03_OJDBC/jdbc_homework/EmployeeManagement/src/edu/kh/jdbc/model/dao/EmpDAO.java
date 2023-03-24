package edu.kh.jdbc.model.dao;

import edu.kh.jdbc.model.dto.Emp;

import java.sql.*;
import java.util.*;

import static edu.kh.jdbc.common.JDBCTemplate.close;

public class EmpDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * 재직중인 전체 직원 정보 확인
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
    public List<Emp> selectAll(Connection conn) throws SQLException {
		String sql = "SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, SALARY, PHONE, EMAIL \n" +
				"FROM EMPLOYEE NATURAL JOIN JOB LEFT JOIN DEPARTMENT ON(DEPT_ID=DEPT_CODE)\n" +
				"WHERE ENT_YN='N' \n" +
				"ORDER BY JOB_CODE";
		List<Emp> empList = new ArrayList<>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Emp e = new Emp();
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setEmpName(rs.getString("EMP_NAME"));
				e.setDepartmentTitle(rs.getString("DEPT_TITLE"));
				e.setJobName(rs.getString("JOB_NAME"));
				e.setSalary(rs.getInt("SALARY"));
				e.setPhone(rs.getString("PHONE"));
				e.setEmail(rs.getString("EMAIL"));
				empList.add(e);
			}
		} finally {
			close(rs);
			close(stmt);
		}

		return empList;
    }

	/**
	 * 퇴직한 사원 전체 조회
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public List<Emp> selectRetiredAll(Connection conn) throws SQLException {
		// 현재 퇴직한 사원의
		// 사번, 이름, 전화번호, 이메일, 퇴사일을
		// 퇴사일 오름차순으로 조회
		String sql = "SELECT EMP_ID, EMP_NAME, PHONE, EMAIL, ENT_DATE\n" +
				"FROM EMPLOYEE WHERE ENT_YN = 'Y' ORDER BY ENT_DATE";
		List<Emp> empList = new ArrayList<>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Emp e = new Emp();
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setEmpName(rs.getString("EMP_NAME"));
				e.setPhone(rs.getString("PHONE"));
				e.setEmail(rs.getString("EMAIL"));
				e.setEntDate(rs.getDate("ENT_DATE").toString());
				empList.add(e);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return empList;
	}

	public Emp selectOneByID(Connection conn, int id) throws SQLException {
		String sql = "SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, SALARY, PHONE, EMAIL, HIRE_DATE, ENT_YN \n" +
				"FROM EMPLOYEE NATURAL JOIN JOB LEFT JOIN DEPARTMENT ON(DEPT_ID=DEPT_CODE) WHERE EMP_ID=?";
		Emp e = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				e = new Emp();
				e.setEmpId(rs.getInt(1));
				e.setEmpName(rs.getString(2));
				e.setDepartmentTitle(rs.getString(3));
				e.setJobName(rs.getString(4));
				e.setSalary(rs.getInt(5));
				e.setPhone(rs.getString(6));
				e.setEmail(rs.getString(7));
				e.setHireDate(rs.getDate(8));
				e.setEntYN(rs.getString(9));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return e;
	}

	/**
	 * 신규 사원 추가
	 * @param conn
	 * @param e
	 * @return
	 * @throws SQLException
	 */
	public int insertEmp(Connection conn, Emp e) throws SQLException {
		String sql = "INSERT INTO EMPLOYEE VALUES(SEQ_EMP_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, NULL, 'N')";
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getEmpName());
			pstmt.setString(2, e.getEmpNo());
			pstmt.setString(3, e.getEmail());
			pstmt.setString(4, e.getPhone());
			pstmt.setString(5, e.getDeptCode());
			pstmt.setString(6, e.getJobCode());
			pstmt.setString(7, e.getSalLevel());
			pstmt.setInt(8, e.getSalary());
			pstmt.setDouble(9, e.getBonus());
			pstmt.setInt(10, e.getManagerId());
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 사원번호로 사원 수정
	 * @param conn
	 * @param emp
	 * @return
	 * @throws SQLException
	 */
	public int updateEmpById(Connection conn, Emp emp) throws SQLException {
		int result = 0;
		String sql = "update EMPLOYEE SET email=?, phone=?, BONUS=?, SALARY=? WHERE emp_id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPhone());
			pstmt.setDouble(3, emp.getBonus());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getEmpId());
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 사원 삭제
	 * @param conn
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteById(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID=?";
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 퇴직 처리
	 * @param conn
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updateRetireById(Connection conn, int id) throws SQLException {
		String sql = "UPDATE EMPLOYEE SET ENT_DATE=SYSDATE, ENT_YN='Y' WHERE EMP_ID=?";
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<Emp> selectLatestEnterTop5(Connection conn) throws SQLException {
		String sql = "SELECT * FROM (\n" +
				"\tSELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '소속없음') DEPT_TITLE, HIRE_DATE\n" +
				"\tFROM EMPLOYEE LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID)\n" +
				"\tORDER BY HIRE_DATE DESC)\n" +
				"WHERE ROWNUM <= 5";
		List<Emp> empList = new ArrayList<>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpId(rs.getInt(1));
				emp.setEmpName(rs.getString(2));
				emp.setDepartmentTitle(rs.getString(3));
				emp.setHireDate(rs.getDate(4));
				empList.add(emp);
			}

		} finally {
			close(rs);
			close(stmt);
		}
		return empList;
	}

	public List<Map<String, String>> departmentStatistics(Connection conn) throws SQLException {
		String sql = "SELECT COUNT(*) PEOPLE, " +
				"NVL(DEPT_TITLE,'소속없음') DEPT_TITLE, " +
				"TO_CHAR(ROUND(AVG(SALARY)), 'L999,999,999') SALARY\n" +
				"FROM EMPLOYEE LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID)\n" +
				"GROUP BY DEPT_TITLE, DEPT_CODE\n" +
				"ORDER BY DEPT_CODE";
		List<Map<String, String>> result = new ArrayList<>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
//				Map<String, String> map = new LinkedHashMap<>();
				map.put("deptTitle", rs.getString("DEPT_TITLE"));
				map.put("people", rs.getString("PEOPLE"));
				map.put("salaryAvg", rs.getString("SALARY").trim());
				result.add(map);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return result;
	}

	/**
	 * 조회 SQL 수행 후 결과 반환
	 * @param conn
	 * @param input
	 * @return
	 * @throws SQLException
	 */
	public int checkEmployee(Connection conn, int input) throws SQLException {
		String sql = "SELECT DECODE(ENT_YN, 'Y', 1, 'N', 2) FROM EMPLOYEE WHERE EMP_ID=?";
		int check = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = rs.getInt("CHECK");
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return check;
	}
}

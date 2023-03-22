package edu.kh.jdbc.run;

import edu.kh.jdbc.dao.SelectDepartmentTitleDAO;
import edu.kh.jdbc.dto.Employee1;

import java.util.List;
import java.util.Scanner;

public class JDBCExample3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 부서명을 입력받아 해당 부서에 존재하는 사원의 사번, 이름, 급여, 부서명을 사번 오름차순으로 조회
        System.out.print("부서명 : ");
        String departmentTitle = sc.nextLine();

        // SelectDepartmentTitleDAO 객체 생성
        SelectDepartmentTitleDAO dao = new SelectDepartmentTitleDAO();
        List<Employee1> empList = dao.select(departmentTitle);

        // 일치하는 부서명이 없어서 조회 결과가 없을 경우
        if(empList.isEmpty()) { // empList.size() == 0
            System.out.println("일치하는 부서가 없습니다.");
            return;
        }

        // 결과 출력
        for(Employee1 e : empList) {
            System.out.printf("%s %s %d %s\n", e.getEmpId(), e.getEmpName(), e.getSalary(), e.getDepartmentTitle());
        }
    }
}

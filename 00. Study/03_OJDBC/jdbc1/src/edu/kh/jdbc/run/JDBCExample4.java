package edu.kh.jdbc.run;

import edu.kh.jdbc.dao.SelectJobNameDAO;
import edu.kh.jdbc.dto.Employee2;

import java.util.List;
import java.util.Scanner;

public class JDBCExample4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("직급명 입력: ");
        String input = sc.nextLine();

        SelectJobNameDAO dao = new SelectJobNameDAO();
        List<Employee2> empList = dao.select(input);

        if(empList.isEmpty())
            System.out.println("입력된 직급명과 일치하는 사원이 없습니다.");

        for(Employee2 e : empList)
            System.out.println(e);
    }
}

package edu.kh.jdbc.view;

import edu.kh.jdbc.model.dto.Employee;
import edu.kh.jdbc.model.service.EmployeeService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// View : 값 입력/출력용 클래스
// -> 사용자와 맞닿는(접점) 클래스 == User Interface(UI)
public class EmployeeView {

    private Scanner sc = new Scanner(System.in);

    private EmployeeService service = new EmployeeService();

    public void displayMenu() {
        int input = 0;

        do {
            try {
                System.out.println("\n-------------------------------");
                System.out.println("-------- 사원 관리 프로그램 --------");
                System.out.println("1. 전체 사원 조회"); // 사번, 이름, 부서명, 직급명, 전화번호 / 직급코드 오름차순
                System.out.println("2. 사번이 일치하는 사원 조회"); // 사번, 이름, 부서명, 직급명, 전화번호 1행 조회
                System.out.println("3. 이름에 글자가 포함된 사원 조회"); // 사번, 이름, 부서명, 직급명, 전화번호 N행 조회 / 사번 오름차순
                System.out.println("4. 급여 범위로 조회"); // 사번, 이름, 직급명, 급여 조회 / 급여 내림차순
                System.out.println("5. 사원 정보 추가"); // INSERT, SEQUENCE 사용
                System.out.println("6. 사번으로 사원 정보 수정"); // 이메일, 전화번호, 급여 수정
                System.out.println("7. 사번으로 사원 퇴사"); // ENT_YN, ENT_DATE 수정
                System.out.println("8. 사번으로 사원 정보 삭제"); // DELETE
                System.out.println("0. 프로그램 종료");
                System.out.println("-------------------------------\n");
                System.out.print("메뉴 선택 >> ");
                input = sc.nextInt();
                sc.nextLine(); // 입력버퍼 개행문자 제거

                switch(input) {
                    case 1: selectAll(); break;
                    case 2: selectOne(); break;
                    case 3: selectName(); break;
                    case 4: selectSalary(); break;
                    case 5: break;
                    case 6: break;
                    case 7: break;
                    case 8: break;
                    case 0: System.out.println("\n[프로그램을 종료합니다...]\n"); break;
                    default: System.out.println("\n[메뉴에 존재하는 번호를 입력하세요.]\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[잘못된 입력입니다.]\n");
                sc.nextLine(); // 입력버퍼에 남아있는 문자열 제거
                input = -1; // while문이 종료되지 않게 하기 위한 값 대입
            }
        } while(input != 0);
    }

    /**
     * 급여 범위로 조회
     */
    private void selectSalary() {
        System.out.println("\n------- 급여 범위로 사원 조회 -------\n");

        System.out.print("최소급여 : ");
        int min = sc.nextInt(); sc.nextLine();
        System.out.print("최대급여 : ");
        int max = sc.nextInt(); sc.nextLine();

        try {
            List<Employee> empList = service.selectSalary(min, max);

            if(empList.isEmpty()) { // 조회된 사원이 없을 경우
                System.out.println("\n[사원이 존재하지 않습니다.]\n");
                return;
            }

            // 향상된 for무을 이용해서 모든 사원 정보 출력
            for (Employee emp : empList) {
                System.out.printf("%d / %s / %s / %d\n",
                        emp.getEmpId(),
                        emp.getEmpName(),
                        emp.getJobName(),
                        emp.getSalary());
            }
        } catch (SQLException e) {
            // 예외를 View까지 넘긴 이유 : 예외 발생 '출력' 구문을 View에서 모아서 처리하기 위해
            System.out.println("\n[급여 범위로 사원 조회 중 예외 발생]\n");
            e.printStackTrace();
        }
    }

    /**
     *  이름에 글자가 포함된 사원 조회
     */
    private void selectName() {
        System.out.println("\n------- 이름에 글자가 포함된 사원 조회 -------\n");

        System.out.print("입력 : ");
        String input = sc.nextLine();

        try {
            List<Employee> empList = service.selectName(input);

            if(empList.isEmpty()) { // 조회된 사원이 없을 경우
                System.out.println("\n[입력된 글자를 포함하는 사원이 존재하지 않습니다.]\n");
                return;
            }

            // 향상된 for무을 이용ㅎ서 모든 사원 정보 출력
            for (Employee emp : empList) {
                System.out.printf("%d / %s / %s / %s / %s\n",
                        emp.getEmpId(),
                        emp.getEmpName(),
                        emp.getDepartmentTitle(),
                        emp.getJobName(),
                        emp.getPhone());
            }
        } catch (SQLException e) {
            // 예외를 View까지 넘긴 이유 : 예외 발생 '출력' 구문을 View에서 모아서 처리하기 위해
            System.out.println("\n[글자가 이름에 포함된 사원 조회 중 예외 발생]\n");
            e.printStackTrace();
        }
    }

    /**
     * 사번으로 사원 조회 (1명)
     */
    private void selectOne() {
        // 사번, 이름, 부서명, 직급명, 전화번호

        System.out.println("\n------- 사번으로 사원 조회 (1명) -------\n");

        System.out.print("사번 입력 : ");
        int input = sc.nextInt();
        sc.nextLine();

        try {
            // 서비스 메서드에 사번을 전달해서 사번이 일치하는 사원 정보를 반환
            Employee emp = service.selectOne(input);

            if(emp == null) { // 조회 결과가 없는 경우
                System.out.println("\n[사번이 일치하는 사원이 없습니다.]\n");
                return;
            }

            System.out.printf("\n%d / %s / %s / %s / %s\n",
                    emp.getEmpId(),
                    emp.getEmpName(),
                    emp.getDepartmentTitle(),
                    emp.getJobName(),
                    emp.getPhone());

        } catch(SQLException e) {
            // 예외를 View까지 넘긴 이유 : 예외 발생 '출력' 구문을 View에서 모아서 처리하기 위해
            System.out.println("\n[사번으로 사원 조회 중 예외 발생]\n");
            e.printStackTrace();
        }
    }

    /**
     * 전체 사원 조회
     */
    private void selectAll() {
        // 사번, 이름, 부서명, 직급명, 전화번호

        System.out.println("\n--------- 전체 사원 조회 ---------\n");

        // DB에서 전체 사원 정보를 조회하는 서비스(selectAll())를 호출하여 결과 반환 받기
        try {
            List<Employee> empList = service.selectAll();

            if(empList.isEmpty()) { // 조회된 사원이 없을 경우
                System.out.println("\n[사원이 존재하지 않습니다.]\n");
                return;
            }

            // 향상된 for무을 이용ㅎ서 모든 사원 정보 출력
            for (Employee emp : empList) {
                System.out.printf("%d / %s / %s / %s / %s\n",
                        emp.getEmpId(),
                        emp.getEmpName(),
                        emp.getDepartmentTitle(),
                        emp.getJobName(),
                        emp.getPhone());
            }
        } catch(SQLException e) {
            // 예외를 View까지 넘긴 이유 : 예외 발생 '출력' 구문을 View에서 모아서 처리하기 위해
            System.out.println("\n[사원 전체 정보 조회 중 예외 발생]\n");
            e.printStackTrace();
        }
    }
}

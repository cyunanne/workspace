package edu.kh.jdbc.view;

import java.sql.SQLException;
import java.util.*;

import edu.kh.jdbc.model.dto.Emp;
import edu.kh.jdbc.model.service.EmpService;

public class EmpView {
  
	private Scanner sc = new Scanner(System.in);
	
	private EmpService service = new EmpService();
	
	//** 모든 기능에는 예외상황에 따른 출력 구문 필수 작성 **
	//** 필요에 따라 DTO에 생성자 추가 **
	//** 메서드명, 출력 화면은 자유롭게 작성 **
	
	public void displayMenu() {
		
		int input = 0;
		
		do {
			
			try {
				System.out.println("\n*****************************\n");
				System.out.println("***** 사원 관리 프로그램 *****");
				
				System.out.println("1. 재직중인 사원 전체 조회"); 
				// 현재 재직중인 사원의
				// 사번, 이름, 부서명, 직급명, 급여, 전화번호, 이메일
				// 직급코드 오름차순으로 조회
				
				
				System.out.println("2. 퇴직한 사원 전체 조회"); 
				// 현재 퇴직한 사원의
				// 사번, 이름, 전화번호, 이메일, 퇴사일을
				// 퇴사일 오름차순으로 조회
				
				
				System.out.println("3. 사번이 일치하는 사원 조회"); 
				// 사번을 입력 받아 일치하는 사원의  
				// 사번, 이름, 부서명, 직급명, 급여, 전화번호, 이메일, 입사일, 퇴직여부 조회
				// 단, 사번이 일치하는 사원이 없으면
				// "사번이 일치하는 사원이 없습니다" 출력
				
				
				System.out.println("4. 사원 정보 추가(INSERT)");
				// 사번(EMP_ID) -> SEQ_EMP_ID SEQUENCE 사용
				
				
				System.out.println("5. 사번으로 사원 정보 수정(UPDATE)");
				// 이메일, 전화번호, 급여, 보너스 수정
				// 단, 사번이 일치하는 사원이 없으면
				// "사번이 일치하는 사원이 없습니다" 출력

				
				System.out.println("6. 사번으로 사원 정보 삭제(DELETE)");
				// 사번을 입력 받아 일치하는 사원 삭제
				// - 사번을 입력 받은 후 정말 삭제할 것인지 Y/N을 입력 받아 
				//   Y인 경우에만 삭제, N인 경우 취소
				// - 사번이 일치하는 사원이 없으면
				//   "사번이 일치하는 사원이 없습니다" 출력
				
				
				System.out.println("7. 사번이 일치하는 사원 퇴직 처리");
				// - ENT_YN -> 'Y' , ENT_DATE -> SYSDATE로 수정
				
				// - 사번을 입력 받은 후 정말 퇴직 처리할 것인지 Y/N을 입력 받아 
				//   Y인 경우에만 퇴직 처리, N인 경우 취소
				
				// - 사번이 일치하지 않거나 이미 퇴직 처리된 사원이면
				//   "사번이 일치하는 않거나, 이미 퇴직된 사원입니다." 출력
				
				System.out.println("8. 가장 최근 입사한 사원 5명 조회");
				
				// 가장 최근(입사일이 늦은) 사원 5명의
				// 사번, 이름, 부서명, 입사일을
				// 입사일 내림차순으로 조회
				
				
				System.out.println("9. 부서별 통계 조회"); 
				// 각 부서별
				// 부서명, 인원 수, 급여 평균
				// 부서코드 오름차순 조회
				
				// HINT.
				// - 별도의 DTO 작성 또는 
				//   Map(LinkedHashMap : key 순서가 유지되는 Map) 이용
				
				
				System.out.println("0. 프로그램 종료");


				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행문자 제거


				switch(input) {
				case 1: selectAll(); break;
				case 2: selectRetiredAll(); break;
				case 3: selectOneById(); break;
				case 4: insertEmp(); break;
				case 5: updateEmpById(); break;
				case 6: deleteById(); break;
				case 7: updateRetireById(); break;
				case 8: selectLatestEnterTop5(); break;
				case 9: departmentStatistics(); break;
				case 0: System.out.println("\n[프로그램을 종료합니다...]\n"); break;
				
				default: System.out.println("\n[메뉴에 존재하는 번호를 입력하세요.]\n");
				}


			}catch (InputMismatchException e) {
				System.out.println("\n[잘못된 입력입니다.]\n");
				sc.nextLine(); // 입력 버퍼에 남아있는 문자열 제거
				input = -1; // while문이 종료되지 않게하기 위한 값 대입
			}
			
		}while(input != 0);
		
	}

	/**
	 * 부서별 통계 조회
	 */
	private void departmentStatistics() {
		// 각 부서별
		// 부서명, 인원 수, 급여 평균
		// 부서코드 오름차순 조회

		// HINT.
		// - 별도의 DTO 작성 또는
		//   Map(LinkedHashMap : key 순서가 유지되는 Map) 이용

		// DTO가 없을 때 Map을 사용하는 이유
		// 1. DTO를 작성하는 게 코드 낭비인 경우
		// 2. DTO와 Map의 구조가 유사하기 때문에
		//   - DTO:Field ≈ Map:Key

		System.out.println("***** 부서별 통계 조회 *****");

		try {
			List<Map<String, String>> statistics = service.departmentStatistics();

			System.out.println("부서명 / 인원 수 / 급여 평균");
			for (Map<String, String> s : statistics) {
				System.out.printf("%s / %s명 / %s\n", s.get("deptTitle"), s.get("people"), s.get("salaryAvg"));
//				Set<String> set = s.keySet();
//				for(String key : set) {
//					System.out.print(s.get(key) + " ");
//				}
//				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("\n[부서별 통계 조회 중 오류 발생]\n");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 가장 최근 입사한 사원 5명 조회
	 */
	private void selectLatestEnterTop5() {
		// 가장 최근(입사일이 늦은) 사원 5명의
		// 사번, 이름, 부서명, 입사일을
		// 입사일 내림차순으로 조회
		System.out.println("***** 가장 최근 입사한 사원 5명 조회 *****");

		try {
			List<Emp> empList = service.selectLatestEnterTop5();

			for (Emp e : empList) {
				System.out.printf("%d / %s / %s / %s\n",
						e.getEmpId(),
						e.getEmpName(),
						e.getDepartmentTitle(),
						e.getHireDate());
			}
		} catch (SQLException e) {
			System.out.println("\n[최근 입사자 조회 중 오류 발생]\n");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 7. 사번이 일치하는 사원 퇴직 처리
	 */
	private void updateRetireById() {
		// - ENT_YN -> 'Y' , ENT_DATE -> SYSDATE로 수정

		// - 사번을 입력 받은 후 정말 퇴직 처리할 것인지 Y/N을 입력 받아
		//   Y인 경우에만 퇴직 처리, N인 경우 취소

		// - 사번이 일치하지 않거나 이미 퇴직 처리된 사원이면
		//   "사번이 일치하는 않거나, 이미 퇴직된 사원입니다." 출력
		System.out.println("***** 사번이 일치하는 사원 퇴직 처리 *****");

		try {
			System.out.print("퇴직을 원하는 사원의 사번 입력 : ");
			int id = sc.nextInt(); sc.nextLine();

			// 1. 사번이 일치하는 사원이 있는지, 있다면 이미 퇴직한 사원인지 화인하는 서비스 호출
			int check = service.checkEmployee(id);
			if(check == 0) {
				System.out.println("\n[사번이 일치하는 사원이 존재하지 않습니다]\n");
				return;
			} else if(check == 1) {
				System.out.println("\n[이미 퇴직 처리된 사원입니다]\n");
				return;
			}

			// 2. 퇴직하지 않은 사원이 존재하면 정말 퇴직처리 할 것인지 확인 후 서비스 호출
			System.out.print("정말로 퇴직 처리 하시겠습니까?(Y/N) : ");
			char doubleCheck = sc.next().toUpperCase().charAt(0);

			if(doubleCheck == 'N') {
				System.out.println("\n[취소되었습니다]\n");
				return;
			} else if(doubleCheck != 'Y') {
				System.out.println("\n[잘못입력하셨습니다]\n");
				return;
			}

			// 앞서 사번 존재여부를 확인했으므로 사번이 없어서 실패하는 경우는 발생하지 않는다.
			// => 업데이트 성공 또는 예외 발생
			service.updateRetireById(id);
			System.out.println("\n[사원 퇴직 처리 완료]\n");

		} catch (SQLException e) {
			System.out.println("\n[사원 퇴직처리 중 오류 발생]\n");
			throw new RuntimeException(e);
		} catch (InputMismatchException e) {
			System.out.println("\n[잘못 입력하셨습니다]\n");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 6. 사번으로 사원 정보 삭제
	 */
	private void deleteById() {
		System.out.println("***** 사번이 일치하는 사원 조회 *****");

		try {
			System.out.print("조회를 원하는 사원의 사번 입력 : ");
			int id = sc.nextInt(); sc.nextLine();
			System.out.print("정말로 삭제하시겠습니까?(Y/N) : ");
			char check = sc.next().toUpperCase().charAt(0);

			if(check == 'N') {
				System.out.println("\n[취소되었습니다]\n");
				return;
			} else if(check != 'Y') {
				System.out.println("\n[잘못입력하셨습니다]\n");
				return;
			}

			int result = service.deleteById(id);

			if(result == 0) System.out.println("\n[사번이 일치하는 사원이 없습니다]\n");
			else            System.out.println("\n[사원 삭제 완료]\n");
		} catch (SQLException e) {
			System.out.println("\n[사원 삭제 중 오류 발생]\n");
			throw new RuntimeException(e);
		} catch (InputMismatchException e) {
			System.out.println("\n[잘못 입력하셨습니다]\n");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 5. 사원 정보 수정
	 */
	private void updateEmpById() {
		System.out.println("***** 사원 정보 수정 *****");

		System.out.print("수정하려는 사원의 사번 입력 : ");
		int id = sc.nextInt(); sc.nextLine();

		try {
			// 수정될 정보를 입력받기 전 해당 사번을 가진 사원이 존재하는지 확인
			boolean empIsExist = (service.selectOneByID(id) != null);
			if( empIsExist ) {
				System.out.println("\n[사번이 일치하는 사원이 없습니다.]\n");
				return;
			}

			System.out.print("이메일 : ");
			String email = sc.next();

			System.out.print("전화번호(-제외) : ");
			String phone = sc.next();

			System.out.print("급여 : ");
			int salary = sc.nextInt();

			System.out.print("보너스 : ");
			double bonus = sc.nextDouble();

			Emp emp = new Emp();
			emp.setEmpId(id);
			emp.setEmail(email);
			emp.setPhone(phone);
			emp.setSalary(salary);
			emp.setBonus(bonus);

			int result = service.updateEmpById(emp);

			if(result == 0)	System.out.println("\n[사원 수정 실패...]\n");
			else 			System.out.println("\n[사원 수정 완료]\n");

		} catch (SQLException e) {
			System.out.println("\n[사원 수정 중 오류 발생]\n");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 사원 정보 추가
	 */
	private void insertEmp() {
		System.out.println("***** 사원 정보 추가 *****");

		System.out.print("이름 : ");
		String empName = sc.next();

		System.out.print("주민등록번호 : ");
		String empNo = sc.next();

		System.out.print("이메일 : ");
		String email = sc.next();

		System.out.print("전화번호(-제외) : ");
		String phone = sc.next();

		System.out.print("부서코드(D1~D9) : ");
		String deptCode = sc.next();

		System.out.print("직급코드(J1~J7) : ");
		String jobCode = sc.next();

		System.out.print("급여등급(S1~S6) : ");
		String salLevel = sc.next();

		System.out.print("급여 : ");
		int salary = sc.nextInt();

		System.out.print("보너스 : ");
		double bonus = sc.nextDouble();

		System.out.print("사수번호 : ");
		int managerId = sc.nextInt();

		Emp emp = new Emp();
		emp.setEmpName(empName);
		emp.setEmpNo(empNo);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setDeptCode(deptCode);
		emp.setJobCode(jobCode);
		emp.setSalLevel(salLevel);
		emp.setSalary(salary);
		emp.setBonus(bonus);
		emp.setManagerId(managerId);

		try {
			int result = service.insertEmp(emp);

			if(result == 0)	System.out.println("\n[사원 추가에 실패했습니다]\n");
			else 			System.out.println("\n[사원 추가 완료]\n");

		} catch (SQLException e) {
			System.out.println("\n[사원 추가 중 오류 발생]\n");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 사번이 일치하는 사원 조회
	 */
	private void selectOneById() {
		System.out.println("***** 사번이 일치하는 사원 조회 *****");

		try {
			System.out.print("조회를 원하는 사원의 사번 입력 : ");
			int id = sc.nextInt(); sc.nextLine();

			Emp e = service.selectOneByID(id);

			if(e == null) {
				System.out.println("\n[사번이 일치하는 사원이 없습니다]\n");
				return;
			}

			System.out.printf("%d / %s / %s / %s / %d / %s / %s / %s / %s\n",
					e.getEmpId(),
					e.getEmpName(),
					e.getDepartmentTitle(),
					e.getJobName(),
					e.getSalary(),
					e.getPhone(),
					e.getEmail(),
					e.getHireDate(),
					e.getEntYN());

			System.out.println("\n[사원 조회 완료]\n");
		} catch (SQLException e) {
			System.out.println("\n[사번으로 사원 조회 중 오류 발생]\n");
			throw new RuntimeException(e);
		} catch (InputMismatchException e) {
			System.out.println("\n[잘못 입력하셨습니다]\n");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 퇴직한 사원 전체 조회
	 */
	private void selectRetiredAll() {
		System.out.println("***** 퇴직한 사원 전체 조회 *****");

		try {
			List<Emp> empList = service.selectRetiredAll();
			for (Emp e : empList) {
				System.out.printf("%d / %s / %s / %s / %s\n",
						e.getEmpId(),
						e.getEmpName(),
						e.getPhone(),
						e.getEmail(),
						e.getEntDate());
			}
			System.out.println("\n[퇴직한 사원 전체 조회 완료]\n");
		} catch (SQLException e) {
			System.out.println("\n[퇴직한 사원 전체 조회 중 오류 발생]\n");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 재직중인 사원 전체 조회
	 */
	private void selectAll() {
		System.out.println("***** 재직중인 사원 전체 조회 *****");

		try {
			List<Emp> empList = service.selectAll();
			for (Emp e : empList) {
				System.out.printf("%d / %s / %s / %s / %d / %s / %s\n",
						e.getEmpId(),
						e.getEmpName(),
						e.getDepartmentTitle(),
						e.getJobName(),
						e.getSalary(),
						e.getPhone(),
						e.getEmail());
			}
			System.out.println("\n[재직중인 사원 전체 조회 완료]\n");
		} catch (SQLException e) {
			System.out.println("\n[재직중인 사원 전체 조회 중 오류 발생]\n");
			throw new RuntimeException(e);
		}
	}
}

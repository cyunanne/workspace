package edu.kh.control.loop.ex;

import java.util.Scanner;

public class WhileEx {

	/* While문
	 * : 별도의 초기식, 증감식이 존재하지 않고 반복조건만을 설정하는 반복문
	 * 
	 * while(조건식) {
	 *     조건식이 true인 경우 반복 수행할 구문
	 * }
	 * */
	
	// while 기초 사용법1: 입력된 모든 정수의 합 출력(종료: 0)
	public void ex1() {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		int input = -1;
		
		while( input != 0 ) {
			System.out.print("정수입력: ");
			input = sc.nextInt();
			sum += input;
			System.out.println("현재 합계: " + sum);
		}
		
		System.out.println("최종 합계: " + sum);
	}
	
	/* while 기초 사용법2: 분식집 주문 프로그램
	 * --- 메뉴 ---
	 * 1. 떡볶이(5000원)
	 * 2. 김밥(3000원)
	 * 3. 라면(4000원)
	 * 9. 주문 완료
	 * 메뉴 선택 >> */
	public void ex2() {
		Scanner sc = new Scanner(System.in);
		String order = "";
		int sum = 0;
		int input = 0;
		
		while( input != 9 ) {
			System.out.println("--- 메뉴 ---");
			System.out.println("1. 떡볶이(5000원)");
			System.out.println("2. 김밥(3000원)");
			System.out.println("3. 라면(4000원)");
			System.out.println("9. 주문 완료");
			System.out.print("메뉴 선택>> ");
			input = sc.nextInt();
			System.out.println();
			
			switch( input ) {
			case 1:
				sum += 5000;
				order += "떡볶이 ";
				break;
			case 2: 
				sum += 3000;
				order += "김밥 ";
				break;
			case 3:
				sum += 4000;
				order += "라면 ";
				break;
			case 9: break;
			default: System.out.println("잘못 입력하셨습니다.");
			}
		}
		
		System.out.println(order + "을/를 주문하셨습니다.");
		System.out.println("총 가격은 " + sum + "원 입니다.");
	}

	/* do-while 기초 사용법1
	 * : 최소 1회 반복을 보장하는 반복문 */
	public void ex3() {
		Scanner sc = new Scanner(System.in);
		int sum = 0, input = 0;
		
		do {
			System.out.print("정수입력: ");
			input = sc.nextInt();
			sum += input;
			System.out.println("현재 합계: " + sum);
		} while( input != 0 );
		
		System.out.println("최종 합계: " + sum);
	}

	// while문을 for문처럼 사용: 1 2 3 ... 10 출력
	public void ex4() {
		
		// 1. for
		for(int i=1; i<=10; i++) {
			System.out.print(i + " ");
		}
		
		System.out.println("\n====================");
		
		// 2. while
		int i=1;
		while( i<=10 ) {
			System.out.print(i++ + " ");
		}
	}
}

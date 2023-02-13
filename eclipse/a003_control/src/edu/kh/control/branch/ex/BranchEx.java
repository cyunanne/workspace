package edu.kh.control.branch.ex;

import java.util.Scanner;

public class BranchEx {

	/* 분기문
	 * 1. break		: 가장 가까운 반복문 탈출
	 * 2. continue	: 가장 가까운 반복문을 시작부분으로 이동(다음 반복 수행)
	 */
	
	// break 예시1
	public void ex1() {
		
		for(int i=1; i<=10000; i++) {
			System.out.println(i);
			
			if(i==20) {
				break;
			}
		}
	}
	
	// break 예시2
	public void ex2() {
		
		Scanner sc = new Scanner(System.in);
		int input = 0, sum = 0;
		
		while( true ) {
			System.out.print("정수 입력: ");
			input = sc.nextInt(); 
			if(input == 0) {
				break;
			}
			sum += input;
		}
		System.out.println("합계: " + sum);
	}
	
	// break 예시3
	public void ex3() {
		
		for(int row=1; row<=5; row++) {
			for(int col=1; col<=5; col++) {
				if(col==3) {
					break;
				}
				System.out.printf("(%d,%d) ", row, col);
			}
			System.out.println();
			if(row==4) {
				break;
			}
		}
	}
	
	// continue 예시1
	public void ex4() {
		
		for(int i=1; i<=10; i++) {
			if(i%2 == 0) {
				continue;
			}
			System.out.print(i + " ");
		}
	}
	
	// 1~100까지 1씩 증가하며 출력, 5의배수 출력X, 40에서 반복문 탈출
	public void ex5() {
		for(int i=0; i<=100; i++) {
			if(i==40) {
				break;
			} else if(i%5==0) {
				continue;
			} 
			System.out.print(i + " ");
		}
	}
	
	// 분기문 응용1: 1~50사이의 난수 발생, 정답까지 걸리는 횟수
	public void upDownGame() {
		
		Scanner sc = new Scanner(System.in);
		int answer = (int)(Math.random() * 50 + 1); // 1<=answer<51
		int count = 0, input = 0;
		
		System.out.println("Game Start!");
		while( true ) {
			System.out.printf("%d번 입력: ", ++count);
			input = sc.nextInt();

			if( input < answer ) {
				System.out.println("UP");
			} else if( input > answer ) {
				System.out.println("DONW");
			} else { // input == answer
				System.out.printf("정답입니다! (총 입력 횟수: %d회)", count);
				break;
			}
		}
	}

	// 분기문 응용2: 시작번호~끝번호 1씩 증가하며 출력, 특정수의 배수를 입력받아 출력모양 변경
	public void ex6() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("시작: ");
		int start = sc.nextInt();
		System.out.print("종료: ");
		int end = sc.nextInt();
		System.out.print("변경할 배수: ");
		int change = sc.nextInt();
		
		for(int i=start; i<=end; i++) {
			if(i%change == 0) {
				System.out.printf("[%d] ", i);
			} else {
				System.out.print(i + " ");
			}
		}
	}
}

package edu.kh.array.ex;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx1 {

	/* 배열(자료구조)
	 * : 같은 자료형의 변수를 하나의 묶음으로 다루는 것
	 *   묶여진 변수들은 하나의 이름(배열명)으로 불리고 각각의 변수는 index를 이용하여 구분
	 *   인덱스는 0부터 시작
	 * */

	// 배열 기본 사용법1
	public void ex1() {

		// 배열 선언: 배열을 참조할 공간을 할당하고, 이름을 arr로 한다.
		// * 기본자료형 8개를 제외한 나머지는 "참조형"이라고 한다.
		int[] arr; // == int arr[]

		// 배열 할당: 실제 배열 공간을 할당하고, 참조값을 arr에 반환.
		arr = new int[4];

		// 배열 이용
		// 1) 배열 각 요소에 값 대입
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;
		arr[3] = 40;

		// 2) 배열 각 요소의 값 얻어오기
		System.out.println("arr[0]: " + arr[0]);
		System.out.println("arr[1]: " + arr[1]);
		System.out.println("arr[2]: " + arr[2]);
		System.out.println("arr[3]: " + arr[3]);

		System.out.println("===============");

		// 3) for문 + 배열
		int sum = 0;
		for(int i=0; i<4; i++) {
			System.out.printf("arr[%d]: %d\n", i, arr[i]);
			sum += arr[i];
		}
		System.out.println("sum: " + sum);
	}

	// 배열 기본 사용법2: 초기화
	public void ex2() {

		int[] numbers = new int[4];
		// 1) int[] numbers	: stack 영역에 int[]를 참조하기 위한 변수 numbers 선언
		// 2) new int[4] 	: Heap 영역에 int 4개짜리 배열을 할당 -> 인덱스 번호 추가, 기본값(0)으로 초기화, 시작주소 부여
		// 3) =				: Heap 영역에 생성된 배열의 시작주소를 numbers에 대입 -> numbers가 배열을 참조함

		// 1. 정말 numbers에 주소가 저장되었을까?
		System.out.println(numbers);

		// 2. 정말 기본값(0)으로 초기화되었을까?
		for(int i : numbers) {
			System.out.println(numbers[i]);
		}

		System.out.println("===========");

		// 배열 초기화
		// 1) 인덱스를 이용한 초기화
		numbers[0] = 100;
		numbers[1] = 5;
		numbers[2] = 300;
		numbers[3] = 99;

		System.out.println("===========");

		// 2) for문을 이용한 초기화
		//    * 배열명.length -> 배열 길이 반환
		for(int i=0; i<numbers.length; i++) {
			numbers[i] = i * 10 + 1;
		}

		System.out.println("===========");

		// 3) 선언과 동시에 초기화
		int[] numbers2 = {100, 200, 300, 400, 500, 600};

		System.out.println("===========");
	}

	// 배열 기본 사용법3: 3명의 키를 입력 받아 평균 구하기
	public void ex3() {
		Scanner sc = new Scanner(System.in);
		double[] heights = new double[3];
		double sum = 0;

		for(int i=0; i<heights.length; i++) {
			System.out.printf("%d번 키 입력 : ", i+1);
			heights[i] = sc.nextDouble();
			sum += heights[i];
		}

		System.out.print("\n입력 받은 키 : ");
		for(int i=0; i<heights.length; i++) {
			System.out.print(heights[i] + " ");
		}

		System.out.printf("\n평균 키 : %.2fcm", sum/3);
	}

	// 배열 기본 사용법4: 오늘의 점심 메뉴 뽑기
	public void ex4() {

		String[] menuArr = {"김밥+라면", "서브웨이", "KFC", "맘스터치", "순대국", "뼈해장국", "닭갈비", "마라탕", "우육면", "파스타", "샐러드"};
		int index = (int)(Math.random() * menuArr.length);
		System.out.println("오늘의 점심 메뉴 : " + menuArr[index]);
	}

	// 배열 기본 사용법5: 주의사항
	public void ex5() {

		// 배열의 범위를 넘어선 인덱스를 참조하는 경우 -> ArrayIndexOutOfBoundsException 발생
		// 문 제 점: for문의 조건식에서 i가 Arr배열의 인덱스 범위를 초과 -> 실행시 for문 내 출력 구문에서 ArrayIndexOutOfBoundsException 발생
		// 해결방법: 조건식을 i < arr.length 로 수정하여 i가 배열의 인덱스 범위를 초과하지 않도록 조치
		int[] arr = {10,30,50,70,90};
		for(int i=0; i<=arr.length; i++)
			System.out.print(arr[i] + " ");
	}

	// 배열 기본 사용법6: 최대/최소 찾기
	public void ex6() {
		Scanner sc = new Scanner(System.in);
		System.out.print("인원 수 : ");
		int input = sc.nextInt();
		int[] arr = new int[input];
		int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		int maxIndex = 0, minIndex = 0;

		for(int i=0; i<input; i++) {
			System.out.print(i+1 + "번 점수 입력 : ");
			arr[i] = sc.nextInt();
			sum += arr[i];

			if( max < arr[i] ) {
				max = arr[i];
				maxIndex = i;
			}
			if( min > arr[i] ) {
				min = arr[i];
				minIndex = i;
			}
		}

		// 최대/최소 구하기
		/*max = min = arr[0];
		for(int i=1; i<input; i++) {
			if( max < arr[i] ) {
				max = arr[i];
				maxIndex = i;
			}
			if( min > arr[i] ) {
				min = arr[i];
				minIndex = i;
			}
		}*/

		System.out.println("\n합계 : " + sum);
		System.out.println("평균 : " + (double)sum/input);
		System.out.printf("최고점 : %d (%d번 학생)\n", max, maxIndex + 1);
		System.out.printf("최저점 : %d (%d번 학생)\n", min, minIndex + 1);
	}

	// 배열 기본 사용법7: 배열 내 데이터 검색
	public void ex7() {
		int[] arr = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		// 1) 초기값을 인덱스 범위에 포함되지 않는 값을 작성
		/*int index = -1;

		for(int i=0; i<arr.length; i++) {
			if( arr[i] == input) {
				index = i;
				break;
			}
		}

		if( index == -1 ) {
			System.out.print("값이 존재하지 않습니다.");
		} else {
			System.out.print("index : " + index);
		}*/

		// 2) flag 변수를 이용하는 방법
		int index = 0;
		boolean flag = true;

		for(int i=0; i<arr.length; i++) {
			if( arr[i] == input) {
				index = i;
				flag = false;
				break;
			}
		}

		if( flag ) {
			System.out.print("값이 존재하지 않습니다.");
		} else {
			System.out.print("index : " + index);
		}
	}

	// 배열 기본 사용법8: 깊은복사/얕은복사
	public void ex8() {

		// 얕은 복사: 참조하는 머..머시기..
		// 깊은 복사: 원본과 같은 자료형, 크기는 같거나 더 큰 배열을 만들어 원본의 데이터를 모두 복사(복제 개념)

		// 얕은 복사 확인
		int[] arr1 = {10,20,30,40,50};
		int[] copyArr1 = arr1; // 얕은 복사

		// 1) 참조하는 주소가 같은가?
		System.out.println("arr1: " + arr1.toString());
		System.out.println("copyArr1: " + copyArr1.toString());

		// 2) 복사본 값을 변경할 경우 원본이 변하는가?
		copyArr1[0] = 9999;

		System.out.println("======================");

		// 깊은 복사 확인
		int[] arr2 = {5,6,7,8};
		int[] copyArr2 = new int[arr2.length];

		// 1) for문 이용
		/*for(int i=0; i<arr2.length; i++) {
			copyArr2[i] = arr2[i]; // 깊은 복사
		}*/

		// 2) System.arraycopy() 이용
		// System.arraycopy(arr2, 0, copyArr2, 0, 0);

		// 3) Array.clone() 이용
		copyArr2 = arr2.clone();
		copyArr2[0] = 9999;
		System.out.println("======================");
	}
}

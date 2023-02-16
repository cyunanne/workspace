package edu.kh.control.condition.ex;

import java.util.Scanner;

public class SwitchEx {

    /* switch문 (switch-case 문)
     * : 식 하나의 결과로  많은 경우의 수를 처리할 때 사용하는 조건문
     *   -> 식의 결과에 맞는 case문이 수행됨
     * : 모든 switch -> if 변환 가능 BUT not all if-else can change switch
     *   
     * [작성법]
     * switch(exp) { exp 수행 결과는 true/false가 아닌 값(정수, 문자열)이다.
     *   case 결과값1:
     *      수행코드1; break;
     *   case 결과값2:
     *      수행코드2; break;
     *   case 결과값3:
     *      수행코드3; break;
     *   default: 모든 케이스가 만족하지 않을 경우 수행하는 코드
     *      기본수행코드;
     * }
     * */
    
    // switch 예제1: 정수입력 -> 1:RED, 2:ORANGE, 3:YELLOW, 4:GEEN, default:BLUE
    public void ex1() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력: ");
        int input = sc.nextInt();
        String result = "";
        
        // 1) if-else
        if( input == 1 ) { result = "RED";
        } else if( input == 2 ) { result = "ORANGE";
        } else if( input == 3 ) { result = "YELLOW";
        } else if( input == 4 ) { result = "GREEN";
        } else { result = "BLUE"; }
        
        System.out.println("if-else: " + result);
        
        // 2) switch
        switch ( input ) {
        case 1: result = "RED"; break;
        case 2: result = "ORANGE"; break;
        case 3: result = "YELLOW"; break;
        case 4: result = "GREEN"; break;
        default:result = "BLUE";     
        }
        
        System.out.println("switch: " + result + '\n');
    }
    
    // switch 예제2: 랜덤 팀 배정 프로그램(백팀, 청팀, 홍팀)
    public void ex2() {
        
        // Math.random() : 자바에서 랜덤 수(난수) 발생시키는 방법
        // 1) 난수 발생 범위 : 0.0 <= number < 1.0
        // 2) 난수의 자료형 : double
        // +) 실수 -> 정수 강제형변환 : 소수점 버림
        
        int random = (int)(Math.random() * 3); // 0~2까지 무작위 숫자 발
        String res = "";
        
        switch( random ) {
        case 0: res = "백팀"; break;
        case 1: res = "청팀"; break;
        default: res = "홍팀";
        }
        
        System.out.println(res + '\n');
    }

    // switch 예제3: the result type of exp is String
    public void ex3() {
    	
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴를 입력하세요(김밥/라면/샌드위치/떡볶이): ");
        String input = sc.next();
        int price = 0;
        
        // 메뉴를 입력받아서 가격 출력
        switch( input ) {
        case "김밥": price = 4500; break;
        case "라면": price = 3000; break;
        case "샌드위치": price = 5500; break;
        case "떡볶이": price = 4800; break;
        default: price = -1; // 잘못 입력한 경우 사용하지 않는 숫자를 이용해서 에러 표
        }
        
        if( price < 0) {
        	System.out.println("존재하지 않는 메뉴입니다.\n");
        } else {
        	System.out.printf("%s은(는) %d원 입니다.\n\n", input, price);
        }
        
    }

    /* switch 예제4: 산술 연산 계산기 만들기
    // 두 정수(int)와 1개의 연산자( + - * / % (String) )를 입력 받아서 
    // 연산 결과를 출력
    // 단, 나누기(/) 연산 시 0으로는 나눌 수 없도록 한다.
    
    // [실행 화면]
    // 정수1 입력 : 5
    // 연산자 입력 : +
    // 정수2 입력 : 4
    // 5 + 4 = 9
    
    // [실행 화면]
    // 정수1 입력 : 5
    // 연산자 입력 : /
    // 정수2 입력 : 0
    // 0으로는 나눌 수 없습니다.
    
    // [실행 화면]
    // 정수1 입력 : 5
    // 연산자 입력 : @
    // 정수2 입력 : 3
    // 존재하지 않는 연산자 입니다.
     */
    public void ex4() {
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.print("정수1 입력: ");
    	int input1 = sc.nextInt();
    	System.out.print("연산자 입력: ");
    	String oper = sc.next();
    	System.out.print("정수2 입력: ");
    	int input2 = sc.nextInt();
    	
    	if( (oper.equals("/") || oper.equals("%")) && input2 == 0 ) {
    		System.out.println("0으로는 나눌 수 없습니다.");
    		return;
    	}
    	
    	switch( oper ) {
    	case "+": System.out.printf("%d + %d = %d", input1, input2, input1+input2); break;
    	case "-": System.out.printf("%d - %d = %d", input1, input2, input1-input2); break;
    	case "*": System.out.printf("%d * %d = %d", input1, input2, input1*input2); break;
    	case "/": System.out.printf("%d / %d = %.2f", input1, input2, input1/(double)input2); break;
    	case "%": System.out.printf("%d %% %d = %d", input1, input2, input1%input2); break;
    	default: System.out.println("존재하지 않는 연산자입니다.");
    	}
    	
    }

    /* switch 예제5: break의 역할
     * 달 입력 -> 계절 판별 스위치버전
     * +) case마다 꼭 코드가 작성될 필요는 없다.
     */
    public void ex5() {
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.print("달 입력: ");
    	int input = sc.nextInt();
    	String str = "";
    	
    	switch( input ) {
    	case 3: case 4: case 5: str = "봄"; break;
    	case 6: case 7: case 8: str = "여름"; break;
    	case 9: case 10: case 11: str = "가을"; break;
    	case 12: case 1: case 2: str = "겨울"; break;
    	default: str = "잘못 입력하셨습니다.";
    	}
    	
    	System.out.println(str);
    }
}

package edu.kh.oop.method.view;

import edu.kh.oop.method.service.MethodExService;

import java.util.Scanner;

public class MethodExView {

    /*
    * View
    * : 프로그램 실행 시 보여지는 부분
    *   콘솔에 내용을 출력, 입력받는 용도의 객체를 만들기 위한 클래스
    * */

    // 클래스 내 어디서든 사용 가능한 Scanner 객체 생성
    private Scanner sc = new Scanner(System.in);
    // 클래스 내 어디서든 사용 가능한 MethodExService 객체 생성
    private MethodExService service = new MethodExService();


    // 메뉴 출력
    public void displayMenu() {

        int input;
        do {
            System.out.println("---------------------");
            System.out.println("1. 매개 변수 X, 반환 값 X");
            System.out.println("2. 매개 변수 O, 반환 값 X");
            System.out.println("3. 매개 변수 X, 반환 값 O");
            System.out.println("4. 매개 변수 O, 반환 값 O");
            System.out.println("0. 프로그램 종료");
            System.out.println("---------------------");
            System.out.print("메뉴 번호 입력 >> ");
            input = sc.nextInt();
            System.out.println();

            switch (input)  {
                case 1: menu1(); break;
                case 2: menu2(); break;
                case 3: menu3(); break;
                case 4: menu4(); break;
                case 0: System.out.println("<프로그램 종료>"); break;
                default: System.out.println("!! 잘못 입력하셨습니다. !!");
            }
        } while(input != 0);

    } // displayMenu() 끝

    // 1. 매개 변수 X, 반환 값 X
    // [접근제한자] [예약어] 반환형 메서드명([매개변수]...) {}
    private void menu1() {
        System.out.println("*** menu1() 실행 ***");

        int a = 10;
        int b = 20;

        System.out.println("a + b = " + (a+b));
    }

    // 2. 매개 변수 O, 반환 값 X
    private void menu2() {
        System.out.println("*** menu2() 실행 ***");
        System.out.println("[ 정수 3개를 입력받아 합계, 평균 출력 ]");
        System.out.print("입력 1 : ");
        int num1 = sc.nextInt();
        System.out.print("입력 2 : ");
        int num2 = sc.nextInt();
        System.out.print("입력 3 : ");
        int num3 = sc.nextInt();

        // service 에서 제공하는 기능
        service.ThreeNumbersSumAndAverage(num1, num2, num3);
    }

    // 3. 매개 변수 X, 반환 값 O
    private void menu3() {
        System.out.println("*** menu3() 실행 ***");
        System.out.println("[ 1~10 사이의 난수 5개를 저장한 배열을 반환받아 출력 ]");
        int[] numbers = service.fiveRandomNumbers(); // 반환값은 얕은복사로 전달

        for(int i=0; i<numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // 메서드 재사용
        service.ThreeNumbersSumAndAverage(numbers[0], numbers[1], numbers[2]);
    }

    // 4. 매개 변수 O, 반환 값 O
    private void menu4() {
        System.out.println("*** menu4() 실행 ***");
        System.out.println("[ 정수 2개, 연산자 입력받아 계산 ]");
        System.out.print("정수 입력 1 : ");
        int num1 = sc.nextInt();
        System.out.print("연산자 입력 : ");
        String op = sc.next();
        System.out.print("정수 입력 2 : ");
        int num2 = sc.nextInt();

        String result = service.calculate(num1, num2, op);
        System.out.println(result);
    }
}

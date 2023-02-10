package edu.kh.control.loop.ex;

import java.util.Scanner;

public class ForEx {

    // for문 기초 사용법2: 5부터 12까지 1씩 증가
    public void ex2() {
        for (int i = 5; i <= 12; i++) {
            System.out.print(i + " ");
        }
    }

    // for문 기초 사용법3: 3부터 20까지 2씩 증가
    public void ex3() {
        for (int i = 3; i <= 20; i += 2) {
            System.out.print(i + " ");
        }
    }

    // for문 기초 사용법4: 1부터 100까지 모든 정수의 합
    public void ex4() {
        int result = 0;
        for (int i = 1; i <= 100; i++) {
            result += i;
        }
        System.out.println(result);
    }

    // for문 기초 사용법5: 두 정수 사이의 모든 숫자의 합 출력
    public void ex5() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수1 입력: ");
        int a = sc.nextInt();
        System.out.print("정수2 입력: ");
        int b = sc.nextInt();

        int result = 0;
        for (int i = a; i <= b; i++) {
            result += i;
        }
        System.out.println(result);
    }

    // for문 기초 사용법6: 다른 자료형으로 for문 사용
    public void ex6() {
        for (double i = 10; i <= 20; i += 0.5) {
            System.out.print(i + " ");
        }

        System.out.println("\n-----------------------------------------");

        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.print(c);
        }
    }

    // for문 응용 사용법1: 감소
    public void ex7() {
        for (int i = 10; i > 0; i--) {
            System.out.print(i + " ");
        }
    }

    // for문 응용 사용법2: 정수 5개를 입력받아 합계 출력
    public void ex8() {
        Scanner sc = new Scanner(System.in);
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            System.out.print((i + 1) + "번째 숫자 입력: ");
            sum += sc.nextInt();
        }
        System.out.println(sum);
    }

    // for문 응용 사용법3: 반복횟수 사용자입력
    public void ex9() {
        Scanner sc = new Scanner(System.in);
        int fin, sum = 0;

        System.out.print("입력 받을 정수의 갯수: ");
        fin = sc.nextInt();

        for (int i = 0; i < fin; i++) {
            System.out.print((i + 1) + "번째 숫자 입력: ");
            sum += sc.nextInt();
        }

        System.out.println(sum);
    }

    // for문 응용 사용법5: 1~10출력, 3배수:[n], 5배수:@
    public void ex11() {
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                System.out.printf("[%d] ", i);
            } else if (i % 5 == 0) {
                System.out.print("@ ");
            } else {
                System.out.print(i + " ");
            }
        }
    }

    // for문 응용 사용법6: 구구단 2단
    public void ex12() {
        for (int i = 1; i <= 9; i++) {
            System.out.printf("2 x %d = %2d\n", i, 2 * i);
        }
    }

    // for문 응용 사용법7: 구구단 입력, 역순 출력
    public void ex13() {
        Scanner sc = new Scanner(System.in);
        System.out.print("구구단 : ");
        int input = sc.nextInt();
        for (int i = 9; i >= 1; i--) {
            System.out.printf("%d × %d = %2d\n", input, i, input * i);
        }
    }

    // for문 응용 사용법8: 구구단 출력
    public void ex14() {
        Scanner sc = new Scanner(System.in);
        System.out.print("구구단 : ");
        int input = sc.nextInt();

        if (input < 2 || input > 9) {
            System.out.println("잘못 입력하셨습니다.");
        } else {
            for (int i = 1; i <= 9; i++) {
                System.out.printf("%d × %d = %2d\n", input, i, input * i);
            }
        }
    }
}

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
}

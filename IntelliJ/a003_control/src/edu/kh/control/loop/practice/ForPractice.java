package edu.kh.control.loop.practice;

import java.util.Scanner;

public class ForPractice {
    public void practice1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("1이상의 숫자를 입력하세요 : ");
        int input = sc.nextInt();

        if(input < 1) {
            System.out.println("1 이상의 숫자를 입력해주세요.");
        }

        for(int i=1; i<=input; i++) {
            System.out.print(i + " ");
        }
    }

    public void practice2() {
        Scanner sc = new Scanner(System.in);
        System.out.print("1이상의 숫자를 입력하세요 : ");
        int input = sc.nextInt();

        if(input < 1) {
            System.out.println("1 이상의 숫자를 입력해주세요.");
        }

        for(int i=input; i>=1; i--) {
            System.out.print(i + " ");
        }
    }

    public void practice3() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수를 하나 입력하세요 : ");
        int input = sc.nextInt();
        int sum = 1;
        String str = "1";

        for(int i=2; i<=input; i++) {
            sum += i;
            str = str + " + " + i;
        }
        System.out.println(str + " = " + sum);
    }

    public void practice4() {
        Scanner sc = new Scanner(System.in);
        System.out.print("첫 번째 숫자 : ");
        int a = sc.nextInt();
        System.out.print("두 번째 숫자 : ");
        int b = sc.nextInt();

        if( a<1 || b<1 ) {
            System.out.println("1 이상의 숫자를 입력해주세요");
            return;
        }

        if( a > b) {
           int tmp = a;
           a = b;
           b = tmp;
        }

        for(int i=a; i<=b; i++) {
            System.out.print(i + " ");
        }
    }

    public void practice5() {
        Scanner sc = new Scanner(System.in);
        System.out.print("숫자 : ");
        int input = sc.nextInt();

        System.out.printf("==== %d단 ====\n", input);
        for(int i=1; i<=9; i++) {
            System.out.printf("%d * %d = %d\n", input, i, input*i);
        }
    }

    public void practice6() {
        Scanner sc = new Scanner(System.in);
        System.out.print("숫자 : ");
        int input = sc.nextInt();

        if( input<2 || input>9 ) {
            System.out.println("2~9 사이 숫자만 입력해주세요.");
            return;
        }

        for(int j=input; j<=9; j++) {
            System.out.printf("==== %d단 ====\n", j);
            for (int i = 1; i <= 9; i++) {
                System.out.printf("%d * %d = %d\n", j, i, j * i);
            }
            System.out.println();
        }
    }

    public void practice7() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력 : ");
        int input = sc.nextInt();

        for(int j=0; j<input; j++) {
            for (int i=0; i<=j; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void practice8() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력 : ");
        int input = sc.nextInt();

        for(int j=input; j>0; j--) {
            for (int i = 0; i < j; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void practice9() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력 : ");
        int input = sc.nextInt();

        for(int j=0; j<input; j++) {
            for(int i=input; i>j; i--) {
                System.out.print(" ");
            }
            for (int i=0; i<=j; i++) {
                System.out.print("*");
            }
            System.out.println();
        }

        /*for(int j=1; j<=input; j++) {
            int i=1;
            for (; i<=input-j; i++) {
                System.out.print(" ");
            }
            for(; i<=input; i++) {
                System.out.print("*");
            }
            System.out.println();
        }*/
    }

    public void practice10() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력 : ");
        int input = sc.nextInt();

        for(int j=0; j<input; j++) {
            for (int i = 0; i <= j; i++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for(int j=input-1; j>0; j--) {
            for (int i = 0; i < j; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void practice11() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력 : ");
        int input = sc.nextInt();

        for(int j=0; j<input; j++) {
            for(int i=input; i>j; i--) {
                System.out.print(" ");
            }
            for (int i = 0; i < (2*j+1); i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void practice12() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력 : ");
        int input = sc.nextInt();

        for(int i=0; i<input; i++) {
            System.out.print("*");
        }
        System.out.println();
        for(int j=0; j<input-2; j++) {
            System.out.print("*");
            for(int i=0; i<input-2; i++) {
                System.out.print(" ");
            }
            System.out.print("*\n");
        }
        for(int i=0; i<input; i++) {
            System.out.print("*");
        }
    }

    public void practice13() {
        Scanner sc = new Scanner(System.in);
        System.out.print("자연수 하나를 입력하세요 : ");
        int input = sc.nextInt();
        int count = 0;

        for(int i=1; i<=input; i++) {
            if( i%2 == 0 || i%3 == 0){
                System.out.print(i + " ");
                if( i%6 == 0) count++;
            }
        }
        System.out.println("\ncount : " + count);
    }
}

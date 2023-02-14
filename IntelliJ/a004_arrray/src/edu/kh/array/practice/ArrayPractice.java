package edu.kh.array.practice;

import java.util.Scanner;

public class ArrayPractice {
    public void practice1() {
        int[] arr = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = i + 1;
            if (i % 2 == 0)
                sum += arr[i];
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n짝수 번째 인덱스 합 : " + sum);
    }

    public void practice2() {
        int[] arr = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = i + 1;
            if (i % 2 == 1)
                sum += arr[i];
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n홀수 번째 인덱스 합 : " + sum);
    }

    public void practice3() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("양의 정수 : ");
        int input = scanner.nextInt();
        int[] arr = new int[input];
        for (int i = 0; i < input; i++) {
            arr[i] = i + 1;
            System.out.print(arr[i] + " ");
        }
    }

    public void practice4() {
        int[] arr = new int[5];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.printf("입력 %d : ", i);
            arr[i] = sc.nextInt();
        }
        System.out.print("검색할 값 : ");
        int search = sc.nextInt();
        for (int i = 0; i < 5; i++) {
            if (arr[i] == search) {
                System.out.println("인덱스 : " + i);
                return;
            }
        }
        System.out.println("일치하는 값이 없습니다.");
    }

    public void practice5() {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자열 : ");
        String input = sc.next();
        System.out.print("문자 : ");
        char search = sc.next().charAt(0);

        char[] arr = new char[input.length()];
        String str = "";
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (arr[i] == search) {
                count++;
                str += i + " ";
            }
            arr[i] = input.charAt(i);
        }

        if (count != 0) {
            System.out.println("application에 " + search + "가 존재하는 위치(인덱스) : " + str);
            System.out.println("i 개수 : " + count);
        } else {
            System.out.print("존재하지 않는 문자입니다.");
        }
    }

    public void practice6() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 : ");
        int input = sc.nextInt();

        int[] arr = new int[input];
        for (int i = 0; i < input; i++) {
            System.out.printf("배열 %d번째 인덱스에 넣을 값 : ", i);
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            sum += arr[i];
        }
        System.out.println("\n총 합 : " + sum);
    }

    public void practice7() {
        Scanner sc = new Scanner(System.in);
        System.out.print("주민등록번호(-포함) : ");
        String input = sc.next();
        char[] arr = input.toCharArray();
        for (int i = 8; i < arr.length; i++)
            arr[i] = '*';
        System.out.println(arr);
    }

    public void practice8() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        while (true) {
            System.out.print("정수(3이상인 홀수) : ");
            input = scanner.nextInt();
            if (input >= 3 && input % 2 == 1) {
                break;
            }
            System.out.println("다시 입력하세요.");
        }

        int[] arr = new int[input];
        int num = 0;
        for (int i = 0; i < input; i++) {
            if (i <= input / 2)
                arr[i] = ++num;
            else
                arr[i] = --num;

            if(i == input-1)
                System.out.println(arr[i]);
            else
                System.out.print(arr[i] + ", ");
        }



    }

    public void practice9() {
    }

    public void practice10() {
    }

    public void practice11() {
    }

    public void practice12() {
    }

    public void practice13() {
    }

    public void practice14() {
    }

    public void practice15() {
    }

    public void practice16() {
    }

    public void practice17() {
    }

    public void practice18() {
    }

    public void practice19() {
    }

    public void practice20() {
    }

    public void practice21() {
    }

    public void practice22() {
    }

    public void practice23() {
    }

    public void practice24() {
    }
}

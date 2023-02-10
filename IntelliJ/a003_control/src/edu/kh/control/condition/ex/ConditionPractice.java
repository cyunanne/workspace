package edu.kh.control.condition.ex;

import java.util.Scanner;

public class ConditionPractice {

    public void practice1() {

        Scanner sc = new Scanner(System.in);
        System.out.print("숫자를 한 개 입력하세요: ");
        int input = sc.nextInt();
        String str = "";

        if (input <= 0) {
            str = "양수만 입력해주세요.";
        } else if (input % 2 == 0) {
            str = "짝수입니다.";
        } else {
            str = "홀수입니다.";
        }

        System.out.println(str);
    }

    public void practice2() {

        Scanner sc = new Scanner(System.in);
        System.out.print("국어: ");
        int kor = sc.nextInt();
        System.out.print("영어: ");
        int en = sc.nextInt();
        System.out.print("수학: ");
        int math = sc.nextInt();

        int sum = kor + en + math;
        double avg = sum / 3.0;

        System.out.printf("국어: %d점, 영어: %d점, 수학: %d점\n", kor, en, math);

        if (kor < 40 || en < 40 || math < 40 || avg < 60)
            System.out.println("불합격입니다.");
        else {

            System.out.printf("총점: %d점, 평균: %.1f점\n", sum, avg);
            System.out.println("축하합니다, 합격입니다!");
        }
    }

    public void practice3() {

        Scanner sc = new Scanner(System.in);
        System.out.print("1~12 사이의 정수 입력: ");
        int month = sc.nextInt();
        int days = 0;

        switch (month) {
            case 2:
                days = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            default:
                days = -1;
        }

        if (days < 0) {
            System.out.printf("%d월은 잘못 입력된 달입니다.", month);
        } else {
            System.out.printf("%d월은 %d일까지 있습니다.", month, days);
        }
    }

    public void practice4() {

        Scanner sc = new Scanner(System.in);
        System.out.print("키(m)를 입력해 주세요: ");
        double height = sc.nextDouble();
        System.out.print("몸무게(kg)를 입력해 주세요: ");
        double weight = sc.nextDouble();

        if (height == 0 || weight == 0) {
            System.out.println("0보다 큰 값을 입력해주세요.");
        }

        double bmi = weight / (height * height);
        String str = "";

        if (bmi < 18.5) {
            str = "저체중";
        } else if (bmi < 23) {
            str = "정상체중";
        } else if (bmi < 25) {
            str = "과체중";
        } else if (bmi < 30) {
            str = "비만";
        } else {
            str = "고도비만";
        }

        System.out.println("BMI지수: " + bmi);
        System.out.println(str);
    }

    public void practice5() {

        Scanner sc = new Scanner(System.in);
        int middleGrade, finalGrade, assignment, attendance;
        double middleGradeConvert, finalGradeConvert, assginmentConvert, attendanceConvert, total;
        String result;

        System.out.print("중간 고사 점수 : ");
        middleGrade = sc.nextInt();
        System.out.print("기말 고사 점수 : ");
        finalGrade = sc.nextInt();
        System.out.print("과제 점수 : ");
        assignment = sc.nextInt();
        System.out.print("출석 횟수 : ");
        attendance = sc.nextInt();

        System.out.println("================= 결과 =================");

        if (attendance < 14) {
            System.out.println("Fail [출석 횟수 부족 (" + attendance + "/20)]");
            return;
        }

        middleGradeConvert = middleGrade * 0.2;
        finalGradeConvert = finalGrade * 0.3;
        assginmentConvert = assignment * 0.3;
        attendanceConvert = attendance / 20 * 100 * 0.2;
        total = middleGradeConvert + finalGradeConvert + assginmentConvert + attendanceConvert;

        if (total < 70) {
            result = "Fail [점수 미달]";
        } else {
            result = "Pass";
        }

        System.out.println("중간 고사 점수(20) : " + middleGradeConvert);
        System.out.println("기말 고사 점수(30) : " + finalGradeConvert);
        System.out.println("과제 점수 (30) : " + assginmentConvert);
        System.out.println("출석 점수 (20) : " + attendanceConvert);
        System.out.println("총점 : " + total);
        System.out.println(result);
    }
}

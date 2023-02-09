package edu.kh.control.condition.ex;

import java.util.Scanner;

public class ConditionEx {

    // if 예시1
    public void ex1() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력: ");
        int input = sc.nextInt();
        
        if( input > 0 ) {
            System.out.println("양수입니다.");
            System.out.println("ex1() 종료");
        }
        
        if( input < 0 ) {
            System.out.println("음수입니다.");
            System.out.println("ex1() 끝!");
        }
    }
    
    // if 예시2: if-else
    public void ex2() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력(ex2):");
        int input = sc.nextInt();
        
        if( input > 0 ) {
            System.out.println("양수입니다.");
        } else {
            if( input == 0 ) {
                System.out.println("0입니다.");
            } else {
                System.out.println("음수입니다.");
            }
        }
        
    }
    
    // if 예시3: if-else if-else
    public void ex3() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("[홀짝 판별기]");
        System.out.print("정수 입력: ");
        int input = sc.nextInt();
        
        if( input == 0 ) {
            System.out.println("0은 홀/짝을 구분할 수 없습니다.");
        } else if( Math.abs(input) % 2 == 1 ) {
            System.out.println("홀수입니다.");
        } else {
            System.out.println("짝수입니다.");
        }
    }
    
    // if 예시4
    public void ex4() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("계절을 알고 싶은 달(월)을 입력해주세요: ");
        int input = sc.nextInt();
        String result = "";
        
        if( input >= 3 && input <= 5) {
            result = "봄";
        } else if( input >= 6 && input <= 8 ) {
            result = "여름";
        } else if( input >= 9 && input <= 11 ) {
            result = "가을";
        } else if( input == 12 || input == 1 || input == 2 ) {
            result = "겨울";
        } else {
           result = "1부터 12사이의 숫자를 입력해주세요.";
        }
        
        System.out.println(result);
    }
    
    // if 예시5: 나이를 입력받아 13세 이하는 어린이, 13세 초과 19세 이하는 청소년, 19세 초과는 성인 출력(0세 이하는무시)
    public void ex5() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("나이를 입력하세요: ");
        int input = sc.nextInt();
        String result = "";
        
        if( input > 0 && input <= 13 ) {
            result = "어린이";
        } else if( input <= 19 ) {
            result = "청소년";
        } else if( input > 19 ) {
            result = "성인";
        } else {
            result ="유효하지 않은 입력입니다.";
        }
        
        System.out.println(result);
    }
    
    // if 예시6: 놀이기구 탑승제한 검사, 12세 이상, 키 140.0cm 이상만 탑승가능
    // 12세 미만 -> "적정 연령이 아닙니다."
    // 키 140.0cm 미만 -> "적정 키가 아닙니다."
    // 나이 0세 미만, 100세 초과 -> "잘못 입력하셨습니다."
    public void ex6() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt();
        String result = "";
        
        if( age < 0 || age > 100 ) {
            result = "잘못 입력하셨습니다.";
        } else if( age < 12 ) {
            result = "적정 연령이 아닙니다.";
        } else {
            System.out.print("키를 입력하세요: ");
            double height = sc.nextDouble();
            
            if( height < 0.0 || height > 200.0 ) {
                result = "잘못 입력하셨습니다.";
            } else if( height < 140.0 ) {
                result = "적정 키가 아닙니다.";
            } else {
                result = "탑승가능합니다.";
            }
        }
        
        System.out.println(result + '\n');
    }

    // if 예시7: 예시6 업그레이드
    public void ex7() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt();
        String result = "";
        
        if( age < 0 || age > 100 ) {
            result = "잘못 입력하셨습니다.";
        } else if( age < 12 ) {
            result = "적정 연령이 아닙니다.";
        } else {
            
            System.out.print("키를 입력하세요: ");
            double height = sc.nextDouble();
            
            if( height < 0.0 || height > 200.0 ) {
                result = "잘못 입력하셨습니다.";
            } else if( height < 140.0 ) {
                result = "적정 키가 아닙니다.";
            } else {
                result = "탑승가능합니다.";
            }
        }
        
        System.out.println(result + '\n');
    }
}

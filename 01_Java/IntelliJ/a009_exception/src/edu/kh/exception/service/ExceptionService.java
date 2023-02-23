package edu.kh.exception.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionService {

    // 예외(Exception) 확인하기
    // thorws IOException : 해당 메서드 내에서 IOException이 발생할 것을 대비한 예외처리 코드
    public void ex1() throws IOException {

        // 키보드(System.in) 입력을 효율적으로 읽어오는 객체
        // Scanner보다 기능은 부족하지만 속도가 빠르다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("입력 : ");

        // readLine(): 한 줄 읽어오기 (개행포함X)
        // readLine() 메서드는 IOException 이라고 하는 예외를 발생시킬(던질) 가능성이 있기 때문에 그 상황에 대한 대비책(예외처리)를 해야한다.
        String input = br.readLine();

        System.out.println(input);

        /*
         * Unchecked Exception
         * - 컴파일 단계에서 예외가 발생할 가능성이 있는지 확인하지 않는 예외
         * - 개발자의 부주의로 발생하며 대부분 쉽게 해결 가능
         * - 치명적인 문제가 아님
         *
         * Checked Exception
         * - 컴파일 단계에서 예외가 발생할 가능성이 있는지 반드시 확인해야 하는 예외
         * - 공식 API 문서의 메서드 설명에 throws OOOException 으로 작성되있는 메서드가 있으면
         *   해당 코드 사용 시 문제가 발생할 것으로 생각하고 그 상황에 대한 예외처리 코드를 반드시 작성해야 한다.
         */

        // java.lang.ArithmeticException: / by zero
        System.out.println(5/0);
    }

    public void ex2() {
        // 예외(Exception): 코드 수정으로 해결 가능한 에러
        // 예외처리: 예외를 처리할 수 있는 구문

        /* 예외처리1: try-catch-finally */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            // try: 예외가 발생할 것 같은 코드를 작성하고 실행 시도
            System.out.print("입력 : ");
            String input = br.readLine();
            throw new IOException(); // 예외 강제발생

        } catch(IOException e) {
            // catch: try 구문 내에서 던져진 예외가 있을 경우 해당 예외를 잡아서 처리
            //        매개변수에는 던져진 예외 객체를 저장할 수 있는 참조 변수를 작성
            //        발생된 예외가 처리된 후 프로그램이 종료되지 않고 다음 코드가 수행됨
            System.out.println("오류가 발생했습니다.");

        }

        System.out.println("try-catch 수행 후 프로그램이 종료되지 않음");
    }

    /** 입력받은 두 정수 나누기 */
    public void ex3() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("입력1: ");
            int num1 = scanner.nextInt();
            System.out.print("입력2: ");
            int num2 = scanner.nextInt();
            System.out.printf("%d / %d = %d\n", num1, num2, num1/num2);
        } catch(ArithmeticException e) { // 산술적 예외 처리
            System.out.println("0으로 나눌 수 없습니다.");
        } catch(InputMismatchException e) {
            // catch는 여러 개 작성할 수 있다.
            System.out.println("입력이 잘못되었습니다.");
        } catch(Exception e) {
            // 주의사항: 상위 타입을 처리하는 catch를 하위 타입을 처리하는 catch 보다 먼저 사용하면 에러 발생(Unreachable code)
            System.out.println("알 수 없는 오류가 발생했습니다.");
        } finally {
            // finally: try-catch 구문이 끝난 후 마지막으로 수행 (예외가 발생하든 말든 무조건 실행)
            System.out.println("프로그램을 종료합니다.");
        }
    }
}

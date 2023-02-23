package edu.kh.oop.method.service;

// 기능 제공용 객체를 만들기 위한 클래스로 비즈니스 로직을 처리
public class MethodExService {

    // 2. 매개 변수 O, 반환 값 X
    // 매개 변수: 전달 받은 값을 저장할 변수
    public void ThreeNumbersSumAndAverage(int a, int b, int c) {
        System.out.println("MethodExService.ThreeNumbersSumAndAverage() 호출");
        int sum = a + b + c;
        double avg = sum / 3.0;

        System.out.println("a : " + a);
        System.out.println("b : " + b);
        System.out.println("c : " + c);
        System.out.println("합계 : " + sum);
        System.out.println("평균 : " + avg);
    }

    // 3. 매개 변수 X, 반환 값 O
    public int[] fiveRandomNumbers() {
        System.out.println("MethodExService.fiveRandomNumbers() 호출");
        int[] arr = new int[5];

        for(int i=0; i<arr.length; i++) {
            arr[i] = (int) (Math.random() * 10 + 1);
        }

        // return: 호출한 위치로 돌아감, 돌아갈 때 가져갈 값이 있으면 return 옆에 작성
        return arr;
    }

    /**
     * 4. 매개 변수 O, 반환 값 O
     * +,-,* : 문제 없음           ---> "5 + 10 = 15"
     * /,% : division by zero   ---> "0으로 나눌 수 없습니다."
     * 그 외 : 연산 불가            ---> "연산 기호가 잘못 입력되었습니다."
     */
    public String calculate(int num1, int num2, String op) {
        // String == 참조형 (null != "")
        // String.format("패턴", 변수); -> 패턴 형태의 문자열을 반환
        String result = String.format("%d %s %d = ", num1, op, num2);

        // 참조형을 비교연산자(==, !=)로 연산하면 참조값을 비교한다.
        // => 참조형의 값 자체를 비교하는 equals() 사용 : boolean a.equals(b)
        if((op.equals("/") || op.equals("%")) && num2==0) { // 우선순위 &&(AND) > ||(OR)
            // 코드 수행 중 return 구문을 만나면 그 즉시 메서드 종료 후 호출지점으로 복귀
            return "0으로 나눌 수 없습니다.";
        }

        switch (op) {
            case "+": result += ( num1 + num2 ); break;
            case "-": result += ( num1 - num2 ); break;
            case "*": result += ( num1 * num2 ); break;
            case "/": result += ( num1 / num2 ); break;
            case "%": result += ( num1 % num2 ); break;
            default: result = "연산 기호가 잘못 입력되었습니다.";
        }
        return result;
    }
}

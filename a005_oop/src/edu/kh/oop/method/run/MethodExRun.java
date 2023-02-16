package edu.kh.oop.method.run;

import edu.kh.oop.method.view.MethodExView;

public class MethodExRun {
    public static void main(String[] args) {
        // MethodExView 객체 생성
        MethodExView view = new MethodExView(); // 기본 생성자(생성자 미작성 시 컴파일러가 자동으로 기본 생성자 추가)

        // 참조변수 view가 참조하는 객체의 기능 중 displayMenu() 메서드 호출
        // .(점 연산자): 참조하는 객체의 필드/메서드에 접근
        view.displayMenu();
    }
}

package edu.kh.polymorphism.ex2.service;

import edu.kh.polymorphism.ex2.dto.*;

public class TestService {
    public void ex1() {

        // 1. 추상 클래스는 진짜로 객체로 못만들까?
        //    -> Cannot instantiate the type Animal (추상클래스이므로)
//        Animal animal = new Animal();

        // 2. Animal을 상속 받은 자식 객체 생성
        Person p1 = new Person("포유류", "잡식", "김뫄뫄", 30);
        p1.breath();

        // 3. 추상 클래스는 객체 생성 X / 부모타입 참조 변수 O (업캐스팅)
        Animal a1 = new Person("포유류", "채식", "박비건", 34);
        a1.breath(); // 동적 바인딩에 의해 Person에 구현된 breath()가 호출됨

        // 4. 객체배열 + 추상 클래스
        Animal[] arr = new Animal[3]; // Animal 참조변수의 배열 (Animal 객체 아님)
        arr[0] = new Fish();
        arr[1] = new Person();
        arr[2] = new Fish();

        // 향상된 for문
        System.out.println("----------------");
        for (Animal a : arr) {
            a.breath();
        }
    }

    public void ex2() {

        // 1. 인터페이스는 상수형 필드(public static final)만 작성 가능 확인
        //    인터페이스에 작성했지만 클래스에 작성하는 static 필드와 사용법 동일
        //    인터페이스의 모든 필드는 묵시적(암묵적)으로 public static final 이다.
        System.out.println(KH.KH_ADDRESS);

        // 2. 인터페이스로 객체 생성 불가, 참조변수 생성 가능
//        KH kh1 = new KH();

        // 객체배열 + 다형성 + 동적바인딩
        KH[] arr = new KH[2];
        arr[0] = new HongGilDong();
        arr[1] = new KimSanSun();
        for(KH kh : arr) {
            kh.lesson();
        }
    }

    // 계산기
    public void ex3() {

        Calculator cal = new YNCalculrator();

        int a = 7;
        int b = 4;
        System.out.println("a = 7, b = 4");

        System.out.println("합 : " + cal.plus(a,b));
        System.out.println("차 : " + cal.minus(a,b));
        System.out.println("곱 : " + cal.multiple(a,b));
        System.out.println("몫 : " + cal.divide(a,b));

        System.out.println("나누기 결과(실수) : " + cal.divide2(a,b));

        System.out.println("----------------------------------");

        int r = 12;
        System.out.println("반지름 = " + r);
        System.out.println("원의 넓이 : " + cal.areaOfCircle(r));

        System.out.println("----------------------------------");

        int num = 2;
        int x = 9;
        System.out.println(num + "의 " + x + "제곱");
        System.out.println("결과 : " + cal.square(num, x));
    }
}

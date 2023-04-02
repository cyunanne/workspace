package edu.kh.polymorphism.ex2.dto;

public interface KH {

    // 상수형 필드(public static final)만 작성 가능
    // 인터페이스는 무조건 상수형 필드만 작성 가능하기 때문에 상수를 나타내는 키워드(public static final)를 생략해도 작성 된것으로 인식된다.
    // = 인터페이스의 모든 필드는 묵시적(암묵적)으로 public static final 이다.
    /*public static final*/ String KH_ADDRESS = "서울시 중구 남대문로 120 2층";

    // 추상 메서드(public abstract)만 작성 가능
    // 인터페이스는 추상클래스의 변형체로, 모든 메서드가 public abstract 이다.
    // = 인터페이스의 모든 메서드는 묵시적(암묵적)으로 public abstract 이다.
    // 단, default 메서드, static 메서드는 abstract가 아니다.
    /*public abstract*/ void lesson();
}
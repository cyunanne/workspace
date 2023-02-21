package edu.kh.oop.field.dto;

// DTO: Data Transfer Object, 계층간 데이터 교환에 사용하는 객체
public class Student {
    /*
     * 필드(속성) 종류
     * 1) 인스턴스 변수: 인스턴스가 생성될 때마다 메모리(heap)에 할당
     * 2) 클래스 변수: JVM 실행 과정에서 클래스 정보를 적재할 때 메모리(static)에 할당
     */

    // 1) 인스턴스 변수
    public String name;
    public int grade;

    public static String schoolName = "KH초등학교";

    public void study() {
        System.out.println("열심히 공부합니다.");
    }
}

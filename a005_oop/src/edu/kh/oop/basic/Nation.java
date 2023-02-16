package edu.kh.oop.basic;

public class Nation {

    // 속성(=멤버변수)
    // 캡슐화 특징2: 멤버변수는 private 접근자 사용
    private String pNo;
    String name;
    char gender;
    String address;
    String phone;
    int age;

    // 메서드
    public void speakKorean() {
        System.out.println("가나다라 한국말 가능");
    }

    public void medicalBenefits() {
        System.out.println("의료 혜택을 받을 수 있다.");
    }

    // 캡슐화 특징2: private으로 지정한 멤버변수에 대한 간접접근 기능
    public String getpNo() {
        return this.pNo;
    }

    public void setpNo(String pNo) {
        this.pNo = pNo;
    }
} // 캡슐화 특징1: 클래스 내에 객체의 속성, 기능을 묶어 둠

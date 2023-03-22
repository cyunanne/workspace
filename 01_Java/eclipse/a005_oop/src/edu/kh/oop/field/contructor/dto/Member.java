package edu.kh.oop.field.contructor.dto;

public class Member {

    // 인스턴스 변수
    private String memberId = "명시적 초기화";
    private String memberPw;
    private String memberName;
    private int memberAge;

    // 클래스 변수
    public static String programName;

    /*
    * 초기화 블럭: 필드를 초기화(값 대입)하는 목적의 블럭, {}
    * 1) 인스턴스 초기화 블럭: 인스턴스 변수를 초기화하는 블럭
    * 2) 클래스 초기화 블럭: 프로그램 실행 시 static변수를 초기화하는 블럭, 프로그램 실행 시 1회만 수행됨
    * 3) 생성자: 인스턴스 생성 시 변수 초기화와 특정 기능 수행 가능
    *
    * 필드 초기화 순서
    * 1) 클래스 변수: (우선순위 낮음) JVM 기본값 -> 명시적 초기값 -> 클래스 초기화블럭 초기값 (우선순위 높음)
    * 2) 인스턴스 변수 : (우선순위 낮음) JVM 기본값 -> 명시적 초기값 -> 인스턴스 초기화블럭 초기값 -> 생성자 (우선순위 높음)
    *
    * 생성자 작성 규칙
    * 1) 반환형이 없다.
    * 2) 생성자명은 클래스와 동일하다.
    * */

    // 1) 인스턴스 초기화 블럭
    {
        memberId = "cyunanne";
        memberPw = "passwd";
        memberName = "최유나";
        memberAge = 31;
    }

    // 2) 클래스 초기화 블럭
    static {
        programName = "회원관리 프로그램";
    }

    // 3) 생성자
    // 3)-a. 기본 생성자
    //   클래스 내에 아무런 생성자도 작성되지 않은 경우 컴파일러가 자동으로 기본 생성자를 추가해준다.
    //   단, 기본 생성자 없이 매개변수 생성자가 존재하면 컴파일러는 기본 생성자를 추가하지 않는다.
    public Member() {
        // 객체 생성 시 특정 기능 수행
        System.out.println("기본 생성자로 생성됨.");

        // 객체 생성 시 필드 초기화
        memberId = "member ID was initialized by constructor";
        memberPw = "1q2w3e4r!";
        memberName = "김누구";
        memberAge = 99;
    }

    // 3)-b. 매개변수 생성자
    public Member(String memberId, String memberPw, String memberName, int memberAge) {
        System.out.println("인스턴스 생성자(String, String, String, int)로 생성됨.");

        // this. : this 참조 변수, 모든 인스턴스에 숨겨져있는 필드로 현재 인스턴스의 시작주소가 저장되어있어 현재 인스턴스를 참조함.
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberAge = memberAge;
    }

    /*
    * 오버로딩(Overloading)
    * : 한 클래스 내에 동일한 이름의 메서드를 여러 개 작성하는 기법
    *
    * 오버로딩 조건
    * 1) 메서드(생성자) 이름이 같아야 한다.
    * 2) (중요) 매개변수의 자료형, 갯수, 순서 중 하나라도 달라야 한다.
    * */

    public Member(String memberId) {
        this(); // this 생성자 --> 중복 제거, 코드길이 감소
        this.memberId = memberId;
    }
    public Member(String memberId, String memberPw) {}
    public Member(String memberId, int memberAge) {}
    public Member(int memberAge, String memberId) {}
//    public Member(int memberAge, String memberName) {} -----(x) 변수명이 달라도 자료형과 순서가 같으면 같은 메소드(생성자)로 취급한다.
}

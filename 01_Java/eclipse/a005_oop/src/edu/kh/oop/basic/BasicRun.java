package edu.kh.oop.basic;

public class BasicRun {
    public static void main(String[] args) {

        // 국민 객체 생성
        // * Object : 클래스에 정의된 내용을 토대로 new 연산자를 통해 생성되어 메모리에 할당
        Nation n1 = new Nation();
        Nation n2 = new Nation();
        Nation n3 = new Nation();

//        n1.pNo = "930524-2384944";
        n1.setpNo("930404-2394022(간접 접근 이용)");
        n1.name = "최유나";
        n1.gender = '여';
        n1.address = "경기도 하남시 아리수로499";
        n1.phone = "010-5923-9363";
        n1.age = 31;

//        System.out.println(n1.pNo);
        System.out.println(n1.getpNo());
        System.out.println(n1.name);
        System.out.println(n1.gender);
        System.out.println(n1.address);
        System.out.println(n1.phone);
        System.out.println(n1.age);

        n1.speakKorean();
        n1.medicalBenefits();
    }
}

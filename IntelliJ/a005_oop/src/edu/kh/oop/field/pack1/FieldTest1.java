package edu.kh.oop.field.pack1;

public class FieldTest1 {

    /*
    * 접근제한자
    *   + public      : 전체, 어디서든지
    *   # protected   : 같은 패키지 + 다른 패키지여도 상속관계면 가능
    *   ~ (default)   : 같은 패키지까지만
    *   - private     : 현재 클래스에서만
    * */

    public      int v1 = 10;
    protected   int v2 = 20;
                int v3 = 30;
    private     int v4 = 40;

    public void ex1() {
        System.out.print(v1);
        System.out.print(v2);
        System.out.print(v3);
        System.out.print(v4);
    }
}

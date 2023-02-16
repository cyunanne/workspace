package edu.kh.oop.field.pack1;

import edu.kh.oop.field.pack2.FieldTest2;
import edu.kh.oop.field.pack2.FieldTest3;

public class FieldTestRun extends FieldTest3 {

    public static void main(String[] args) {
        FieldTest1 fieldTest1 = new FieldTest1();

        // 다른 패키지에 있는 클래스를 사용하기 위해서는 import 필요
        FieldTest2 fieldTest2 = new FieldTest2();
        FieldTest3 fieldTest3 = new FieldTest3();

        System.out.print(fieldTest1.v1);
        System.out.print(fieldTest1.v2);
        System.out.print(fieldTest1.v3);

        // private으로 선언한 필드는 다른 클래스에서 접근 불가
        // System.out.print(fieldTest1.v4);

        // protected로 선언된 필드/메소드는 내부 필드/메소드처럼 사용
        // System.out.print(fieldTest3.v2);
        System.out.print(v2);
    }
}

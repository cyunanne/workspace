package edu.kh.oop.field.run;

import edu.kh.oop.field.dto.Student;

public class StudentRun {
    public static void main(String[] args) {
        Student student = new Student();
        student.name = "김뫄뫄";
        student.grade = 7;

        Student student2 = new Student();
        student2.name = "박모모";
        student2.grade = 5;

        System.out.println(student.name);
        System.out.println(student.grade);
        System.out.println(student.schoolName);

        System.out.println("-------------------");

        System.out.println(student2.name);
        System.out.println(student2.grade);
        System.out.println(student2.schoolName);

        System.out.println("-------------------");

        // 클래스 변수에 저장된 값 변경
        // 참조변수를 이용해서 static으로 지정된 필드 값을 바꿀 수 있으나
        // 'class명.field명'을 이용해서 다루는 것을 권장
        // student.schoolName = "KH국민학교"; ----- (△)
        Student.schoolName = "KH국민학교"; // ----- (ㅇ)
        System.out.println(student.schoolName);
        System.out.println(student2.schoolName);
    }
}

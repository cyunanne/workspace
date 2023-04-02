package edu.kh.oop.field.constructor.run;

import edu.kh.oop.field.contructor.dto.Member;

public class ConstructorRun {
    public static void main(String[] args) {
        Member member1 = new Member();
        Member member2 = new Member("ididid", "pwpwpw", "박모씨", 16);
        Member member3 = new Member("user9999");

        System.out.println(Member.programName);
        System.out.println("--------------");
    }
}

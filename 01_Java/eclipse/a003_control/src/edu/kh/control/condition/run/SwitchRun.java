package edu.kh.control.condition.run;

import edu.kh.control.condition.ex.SwitchEx;

import java.util.Scanner;

public class SwitchRun {

    public static void main(String[] args) {
        
    	Scanner sc = new Scanner(System.in);
        SwitchEx sw = new SwitchEx();
        
        System.out.print("프로그램 선택(1~5): ");
        int menu = sc.nextInt();
        
        switch( menu ) {
        case 1: sw.ex1(); break;
        case 2: sw.ex2(); break;
        case 3: sw.ex3(); break;
        case 4: sw.ex4(); break;
        case 5: sw.ex5(); break;
        default: System.out.println("입력오류");
        }
    }
}

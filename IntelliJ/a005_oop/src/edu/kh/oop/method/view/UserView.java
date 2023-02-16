package edu.kh.oop.method.view;

import edu.kh.oop.method.dto.User;
import edu.kh.oop.method.service.UserService;

import java.util.Scanner;

/**
 * 입출력용 객체를 만들기 위한 클래스
 * */
public class UserView {

    private Scanner sc = new Scanner(System.in);
    private UserService service = new UserService();
    private User signUpUser = null; // 가입할 사용자 정보를 가지고 있는 객체를 참조할 변수 선언
    public static User loginUser = null; // static : 어디서든지 클래스명.필드명으로 접근 가능

    public void displayMenu() {

        int input = 0; // 입력한 메뉴 번호를 저장할 변수

        do {
            System.out.println("---- 사용자 기능 메뉴 ----");
            System.out.println("0. 프로그램 종료");
            System.out.println("----------------------");
            System.out.print("메뉴 선택 >> ");
            input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 0: break;
                default:
            }
        } while(input != 0);
    }
}

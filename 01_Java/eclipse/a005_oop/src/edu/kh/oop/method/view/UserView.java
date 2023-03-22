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

    // 기본생성자
    public UserView() {
        // 기본 생성자로 객체 생성 시 signUpUser에 객체 주소 대입
        signUpUser = new User("user", "pass", "최유나", 'F');
    }

    public void displayMenu() {

        int input = 0; // 입력한 메뉴 번호를 저장할 변수

        do {
            System.out.println("---- 사용자 기능 메뉴 ----");
            System.out.println("1. 회원 가입");
            System.out.println("2. 로그인");
            System.out.println("3. 로그아웃");
            System.out.println("4. 회원 정보 출력");
            System.out.println("5. 회원 정보 수정");
            System.out.println("0. 프로그램 종료");
            System.out.println("----------------------");
            System.out.print("메뉴 선택 >> ");
            input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1: this.signUp(); break;
                case 2: this.login(); break;
                case 3: this.logout(); break;
                case 4: this.userPrint(); break;
                case 5: this.userUpdate(); break;
                case 0: System.out.println("<프로그램 종료>"); break;
                default: System.out.println("잘못 입력하셨습니다.");
            }
            System.out.println();
        } while(input != 0);
    }

    // 1. 회원 가입
    public void signUp() {
        System.out.println("*** 회원 가입 ***");
        System.out.print("ID : ");
        String userId = sc.next();
        System.out.print("PW : ");
        String userPw = sc.next();
        System.out.print("PW 확인 : ");
        String userPwConfirm = sc.next();
        System.out.print("이름 : ");
        String userName = sc.next();
        System.out.print("성별(M/F) : ");
        char userGender = sc.next().charAt(0);

        signUpUser = service.signUp(userId, userPw, userPwConfirm, userName, userGender);
        if( signUpUser != null ) {
            System.out.println("회원가입 성공!");
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    }

    // 2. 로그인
    public void login() {
        System.out.println("*** 로그인 ***");

        // 회원가입 된 유저가 없을 경우
        if( signUpUser == null ) {
            System.out.println("먼저 회원 가입 후 진행해주세요.");
            return; // 현재 메서드를 종료하고 호출한 곳으로 돌아감
        }

        System.out.print("ID : ");
        String userId = sc.next();
        System.out.print("PW : ");
        String userPw = sc.next();

        service.login(userId, userPw, signUpUser);
        if( UserView.loginUser != null ) {
            System.out.println(UserView.loginUser.getUserName() + "님 환영합니다.");
        } else {
            System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    // 3. 로그아웃
    private void logout() {
        if(UserView.loginUser != null) {
            UserView.loginUser = null;
            System.out.println("로그아웃 되었습니다.");
        } else {
            System.out.println("로그인 후 이용해주세요.");
        }
    }

    // 4. 회원 정보 출력
    private void userPrint() {
        System.out.println("*** 회원 정보 출력 ***");

        if(UserView.loginUser != null) {
            System.out.println(UserView.loginUser.toString());
        } else {
            System.out.println("로그인 후 이용해주세요.");
        }
    }

    // 5. 회원 정보 수정
    private void userUpdate() {
        System.out.println("*** 회원 정보 수정***");

        if(UserView.loginUser != null) {
            System.out.print("이름 : ");
            String userName = sc.next();
            System.out.print("성별(M/F) : ");
            char userGender = sc.next().charAt(0);
            System.out.print("PW : ");
            String userPw = sc.next();

            if (service.userUpdate(userName, userGender, userPw)) {
                System.out.println("회원 정보가 수정되었습니다.");
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        } else {
            System.out.println("로그인 후 이용해주세요.");
        }
    }
}
package edu.kh.jdbc.main.view;

import edu.kh.jdbc.board.view.BoardView;
import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.view.MemberView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    private Scanner scanner = new Scanner(System.in);
    private MainService service = new MainService();

    private MemberView memberView = new MemberView(); // 회원 기능 화면 객체 생성
    private BoardView boardView = new BoardView(); // 게시판 기능 화면 객체 생성

    /**
     * 메인 메뉴 출력
     */
    public void mainMenu() {
        int input = 0;
        do {
            try {
                if(Session.loginMember == null) {
                    // 로그아웃 상태 화면 출력
                    System.out.println("\n=== 회원제 게시판 프로그램 ===\n");
                    System.out.println("1. 로그인");
                    System.out.println("2. 회원가입");
                    System.out.println("0. 프로그램 종료");

                    // 메뉴 사용자 입력
                    System.out.print("\n메뉴 선택 : ");
                    input = scanner.nextInt();
                    scanner.nextLine(); // 입력 버퍼 개행 문자 제거

                    // 메뉴 선택에 따른 메서드 호출
                    switch (input) {
                        case 1: login(); break;
                        case 2: signUp(); break;
                        case 0: System.out.println("\n=== 프로그램 종료 ===\n"); break;
                        default: System.out.println("\n*** 메뉴 번호만 입력해 주세요 ***\n");
                    }
                } else {
                    // 로그인 상태 화면 출력
                    System.out.println("\n=== 로그인 메뉴 ===\n");
                    System.out.println("1. 회원 기능");
                    System.out.println("2. 게시판 기능");
                    System.out.println("3. 로그아웃");
                    System.out.println("0. 프로그램 종료");

                    // 메뉴 사용자 입력
                    System.out.print("\n메뉴 선택 : ");
                    input = scanner.nextInt();
                    scanner.nextLine(); // 입력 버퍼 개행 문자 제거

                    // 메뉴 선택에 따른 메서드 호출
                    switch (input) {
                        case 1: memberView.memberMenu(); break;
                        case 2: boardView.boardMenu(); break;
                        case 3:
                            System.out.println("\n=== 로그아웃 되었습니다 ===\n");
                            Session.loginMember = null; // 참조 중이던 로그인 회원 객체 제거
                            break;
                        case 0: System.out.println("\n=== 프로그램 종료 ===\n"); break;
                        default: System.out.println("\n*** 메뉴 번호만 입력해 주세요 ***\n");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\n*** 입력 형식이 올바르지 않습니다. ***\n");
                scanner.nextLine(); // 입력 버퍼에 잘못된 문자열 제거
                input = -1; // while 종료 방지
            }
        } while(input != 0);
    }

    /**
     * 회원가입
     */
    private void signUp() {
        System.out.println("\n[회원가입]\n");

        // 아이디, 비밀번호, 이름, 성별(M/F)을 저장할 변수 선언
        String memberId = null;
        String memberPw = null;
        String pwConfirm = null; // 비밀번호 확인용 변수
        String memberName = null;
        String memberGender = null;

        try {
            // 아이디 입력
            // - 탈퇴하지 않은 회원 중 같은 아이디가 존재하면 중복으로 판정
            // - 중복되지 않은 아이디를 입력할때까지 무한 반복
            while(true) {
                System.out.print("아이디 입력 : ");
                memberId = scanner.next();
                // 아이디 중복 확인 서비스 호출
                // 중복인 경우 1, 아닌 경우 0 반환
                int result = service.idDuplicationCheck(memberId);
                // 중복 검사 결과에 따라 반복 제어
                if(result == 0) {
                    System.out.println("\n=== 사용 가능한 아이디 입니다 ===\n");
                    break;
                } else {
                    System.out.println("\n*** 중복된 아이디 입니다 ***\n");
                }
            }

            // 비밀번호 입력
            // 비밀번호와 비밀번호 확인이 같을 때까지 무한 반복
            while(true) {
                System.out.print("비밀번호 입력 : ");
                memberPw = scanner.next();
                System.out.print("비밀번호 확인 입력 : ");
                pwConfirm = scanner.next();
                // 비밀번호 일치 여부에 따라 반복 제어
                if(memberPw.equals(pwConfirm)) {
                    System.out.println("\n=== 비밀번호가 일치합니다 ===\n");
                    break;
                }
                System.out.println("\n*** 비밀번호가 일치하지 않습니다  ***\n");
            }

            // 이름 입력
            System.out.print("이름 입력 : ");
            memberName = scanner.next();

            // 성별 입력
            // M 또는 F가 입력될 때까지 무한 반복
            while(true) {
                System.out.print("성별 입력(M/F) : ");
                memberGender = scanner.next().toUpperCase();
                // 정상/비정상 입력에 따라 반복 제어
                if(memberGender.equals("M") || memberGender.equals("F")) break;
                System.out.println("\n*** M 또는 F만 입력할 수 있습니다 ***\n");
            }

            // Member 객체를 생성하여 입력받은 값 저장
            Member member = new Member();
            member.setMemberId(memberId);
            member.setMemberPw(memberPw);
            member.setMemberName(memberName);
            member.setMemberGender(memberGender);

            // 회원가입 서비스 호출
            int result = service.signUp(member);
            if(result > 0) System.out.println("\n=== 회원 가입 성공 ===\n");
            else           System.out.println("\n*** 회원 가입 실패 ***\n");

        } catch(Exception e) {
            System.out.println("\n*** 회원가입 중 예외 발생 ***\n");
            e.printStackTrace();
        }
    }

    /**
     * 로그인
     */
    private void login() {
        System.out.println("\n[로그인]\n");
        System.out.print("아이디 : ");
        String memberId = scanner.next();
        System.out.print("비밀번호 : ");
        String memberPw = scanner.next();

        try {
            // 로그인 서비스 호출 후 반환 결과를 Session.loginMember 에 저장
            Session.loginMember = service.login(memberId, memberPw);

            if(Session.loginMember == null) {
                System.out.println("\n*** 아이디/비밀번호가 일치하지 않습니다 ***\n");
            } else {
                System.out.printf("\n=== %s님 환영합니다 ===\n",
                        Session.loginMember.getMemberName());
            }
        } catch (Exception e) {
            System.out.println("\n*** 로그인 중 예외 발생 ***\n");
            e.printStackTrace();
        }
    }
}
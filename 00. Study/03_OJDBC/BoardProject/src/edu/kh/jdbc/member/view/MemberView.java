package edu.kh.jdbc.member.view;

import edu.kh.jdbc.member.model.service.MemberService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 회원 전용 화면
 * @author 최유나(cyunanne@gmail.com)
 */
public class MemberView {

    private Scanner scanner = new Scanner(System.in);
    private MemberService service = new MemberService();

    /**
     * 메뉴 화면 출력
     */
    public void memberMenu() {
        int input = 0;
        do {
            try {
                System.out.println("\n=== 회원 기능 ===\n");
                System.out.println("1. 내 정보 조회");
                System.out.println("2. 회원 목록 조회(아아디, 이름, 성별)");
                System.out.println("3. 내 정보 수정(이름, 성별)");
                System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
                System.out.println("5. 회원 탈퇴(보안코드, 비밀번호, UPDATE)");
                System.out.println("9. 메인 메뉴로 돌아가기");
                System.out.println("0. 프로그램 종료");

                // 메뉴 사용자 입력
                System.out.print("\n메뉴 선택 : ");
                input = scanner.nextInt();
                scanner.nextLine(); // 입력 버퍼 개행 문자 제거

                switch(input) {
                    case 1: break;
                    case 2: break;
                    case 3: break;
                    case 4: break;
                    case 5: break;
                    case 9: System.out.println("\n=== 메인 메뉴로 돌아갑니다 ===\n"); break;
                    case 0: System.out.println("\n=== 프로그램 종료 ===\n"); System.exit(0);
                    default: System.out.println("\n*** 메뉴 번호만 입력해 주세요 ***\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n*** 입력 형식이 올바르지 않습니다. ***\n");
                scanner.nextLine(); // 입력 버퍼에 잘못된 문자열 제거
                input = -1; // while 종료 방지
            }
        } while(input != 9);
    }
}

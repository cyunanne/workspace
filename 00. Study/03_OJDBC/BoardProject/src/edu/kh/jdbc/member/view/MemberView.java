package edu.kh.jdbc.member.view;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;

import java.util.InputMismatchException;
import java.util.List;
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
                    case 1: selectMyInfo(); break;
                    case 2: selectMemberList(); break;
                    case 3:  break;
                    case 4:  break;
                    case 5:  break;
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

    /**
     * 회원 목록 조회
     */
    private void selectMemberList() {
        System.out.println("\n=== 회원 목록 조회 ===\n");
        try {
            List<Member> memberList = service.selectMemberList();
            if(memberList.isEmpty()) {
                System.out.println("\n=== 조회 결과가 없습니다 ===\n");
                return;
            }
            for(int i=0; i<memberList.size(); i++) {
                System.out.printf("%d\t\t%s\t\t%s\t\t%s\n",
                        i+1,
                        memberList.get(i).getMemberId(),
                        memberList.get(i).getMemberName(),
                        memberList.get(i).getMemberGender());
            }
        } catch (Exception e) {
            System.out.println("\n*** 회원 목록 조회 중 예외 발생 ***\n");
            e.printStackTrace();
        }
    }

    /**
     * 내 정보 조회
     * 회원번호, 아이디, 이름, 성별(남/여), 가입일
     */
    private void selectMyInfo() {
        System.out.println("\n=== 내 정보 조회 ===\n");

        // Session.loginMember 이용
        System.out.println("회원번호 : " + Session.loginMember.getMemberNo());
        System.out.println("아이디 : " + Session.loginMember.getMemberId());
        System.out.println("이름 : " + Session.loginMember.getMemberName());
        System.out.println("성별 : " + (Session.loginMember.getMemberGender().equals("M") ? "남" : "여"));
        System.out.println("가입일 : " + Session.loginMember.getEnrollDate());
    }
}

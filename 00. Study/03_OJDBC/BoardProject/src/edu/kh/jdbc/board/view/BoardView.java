package edu.kh.jdbc.board.view;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.common.Session;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BoardView {

    private BoardService service = new BoardService();

    private Scanner sc = new Scanner(System.in);

    public void boardMenu() {
        int input = -1;

        do {
            try {
                System.out.println("\n===== 게시판 기능 =====\n");
                System.out.println("1. 게시글 목록 조회");
                System.out.println("2. 게시글 상세 조회(+ 댓글 기능)");
                System.out.println("3. 게시글 작성");
                // 3. 게시글 작성
                // 제목, 내용(StringBuffer 이용) 입력
                // -> 게시글 삽입 서비스(제목, 내용, 로그인 회원 번호) 호출

                System.out.println("4. 게시글 검색");
                // 4. 게시글 검색

                System.out.println("9. 메인 메뉴로 돌아가기");
                System.out.println("0. 프로그램 종료");

                System.out.print("\n메뉴 선택 : ");
                input = sc.nextInt();
                sc.nextLine();

                System.out.println();

                switch(input) {
                    case 1: selectAllBoard();  break; // 게시글 목록 조회
                    case 2: selectBoard(); break; // 게시글 상세 조회
                    case 3: insertBoard(); break; // 게시글 등록(삽입)
//                    case 4:   searchBoard(); break; // 게시글 검색
                    case 9:
                        System.out.println("\n===== 메인 메뉴로 돌아갑니다 =====\n");
                        break;

                    case 0:
                        System.out.println("\n=== 프로그램 종료 ===\n");
                        System.exit(0);
                    default: System.out.println("\n*** 메뉴 번호만 입력 해주세요 ***\n");
                }

                System.out.println();

            }catch (InputMismatchException e) {
                System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
                sc.nextLine(); // 입력버퍼에 잘못된 문자열 제거
                input = -1; // while문 종료 방지
            }

        }while(input != 9);
    }

    /**
     * 3. 게시글 작성(INSERT)
     */
    private void insertBoard() {
        System.out.println("\n=== 게시글 작성 ===\n");

        // 제목 입력
        System.out.print("제목 입력 : ");
        String title = sc.nextLine();
        // 내용 입력(StringBuffer)
        System.out.print("<wq! 입력 시 종료> : ");
        StringBuffer contents = new StringBuffer();
        while(true) {
            String str = sc.nextLine();
            if(str.equals("wq!")) break;
            contents.append(str);
            contents.append("\n");
        }

        // 게시글 삽입 서비스 호출
        try {
            int result = service.insertBoard(title, contents.toString(), Session.loginMember.getMemberNo());
            if( result > 0 ) {
                // 등록 성공 시 게시글 상세조회 서비스 호출
                System.out.println("\n=== 게시글이 등록되었습니다 ===\n");
                Board board = service.selectBoard(result, Session.loginMember.getMemberNo());
                System.out.println("--------------------------------------------------------");
                System.out.printf("글번호 : %d \n제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
                System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n",
                        board.getMemberName(), board.getCreateDate(), board.getReadCount());
                System.out.println("--------------------------------------------------------\n");
                System.out.println(board.getBoardContent());
                System.out.println("\n--------------------------------------------------------");
            } else {
                System.out.println("\n*** 게시글 등록 실패 ***\n");
            }
        } catch(Exception e) {
            System.out.println("\n*** 게시글 등록 중 예외 발생 ***\n");
            e.printStackTrace();
        }
    }

    /**
     * 2. 게시글 상세 조회
     */
    private void selectBoard() {
        System.out.println("\n=== 게시글 상세 조회 ===\n");
        // 게시글 번호 입력
        // 1) 번호가 일치하는 게시글이 있으면 조회
        //  -> 조회수 증가(단, 자신이 작성한 게시글일 경우 조회수 증가 X)
        //  -> 자신이 작성한 게시글일 경우 수정/삭제 기능 노출
        // (+ 댓글 목록/댓글 기능 추가 예정)
        // 2) 번호가 일치하는 게시글이 없으면
        //  -> 해당 게시글이 존재하지 않습니다
        System.out.print("게시글 번호 입력 : ");
        int input = sc.nextInt(); sc.nextLine();

        try {
            // 게시글 상세 조회 서비스 호출
            Board board = service.selectBoard(input, Session.loginMember.getMemberNo());
            if( board == null ) {
                System.out.println("\n=== 해당하는 게시글이 존재하지 않습니다 ===\n");
                return;
            }
            System.out.println("--------------------------------------------------------");
            System.out.printf("글번호 : %d \n제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
            System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n",
                    board.getMemberName(), board.getCreateDate(), board.getReadCount());
            System.out.println("--------------------------------------------------------\n");
            System.out.println(board.getBoardContent());
            System.out.println("\n--------------------------------------------------------");

            // 로그인한 회원이 작성한 게시글일 경우
            // 게시글 수정/삭제 기능 노출
            if( Session.loginMember.getMemberNo() == board.getMemberNo()) {
                System.out.println("1) 수정");
                System.out.println("2) 삭제");
                System.out.println("0) 게시판 메뉴로 돌아가기");
                System.out.print("선택 >> ");
                input = sc.nextInt(); sc.nextLine();

                switch(input) {
                    case 1: updateBoard(board.getBoardNo()); break;
                    case 2: deleteBoard(board.getBoardNo()); break;
                    case 0: return;
                    default: System.out.println("\n*** 잘못 입력하셨습니다 ***\n");
                }
            }
        } catch(Exception e) {
            System.out.println("\n*** 게시글 상세 조회 중 예외 발생 ***\n");
            e.printStackTrace();
        }
    }

    /**
     * 1. 게시글 삭제
     * @param boardNo
     */
    private void deleteBoard(int boardNo) {
        System.out.println("\n=== 게시글 삭제 ===\n");

        while(true) {
            System.out.print("정말 삭제하시겠습니까?(Y/N) : ");
            char check = sc.next().toUpperCase().charAt(0);

            if(check == 'N') {
                System.out.println("[삭제 취소]");
                return;
            } else if(check != 'Y') {
                System.out.println("[잘못 입력하셨습니다]");
                continue;
            }
            break; // check == 'Y' 인 경우
        }

        // 게시글 삭제 서비스 호출
        try {
            int result = service.deleteBoard(boardNo);
            if(result > 0 ) {
                System.out.println("\n=== 삭제 되었습니다 ===\n");
            } else {
                System.out.println("\n*** 삭제 실패 ***\n");
            }
        } catch(Exception e) {
            System.out.println("\n*** 게시글 삭제 중 예외 발생 ***\n");
            e.printStackTrace();
        }
    }

    /**
     * 게시글 수정
     * @param boardNo
     */
    private void updateBoard(int boardNo) {
        System.out.println("\n=== 게시글 수정 ===\n");

        System.out.print("수정할 제목 : ");
        String boardTitle = sc.nextLine();
        // 특정 단어(!wq)가 입력될 때 까지 무한 입력
        System.out.print("<wq! 입력 시 종료> : ");
        StringBuffer buffer = new StringBuffer();
        while(true) {
            String str = sc.nextLine();
            if(str.equals("wq!")) break;
            buffer.append(str);
            buffer.append("\n");
        }

        // 게시글 수정 서비스 호출
        try {
            int result = service.updateBoard(boardNo, boardTitle, buffer.toString());
            if(result == 0 ) {
                System.out.println("\n=== 게시글이 수정 되었습니다 ===\n");
            } else {
                System.out.println("\n*** 게시글이 수정 실패 ***\n");
            }
        } catch(Exception e) {
            System.out.println("\n*** 게시글 수정 중 예외 발생 ***\n");
            e.printStackTrace();
        }
    }

    /**
     * 게시글 목록 조회
     */
    private void selectAllBoard() {
        System.out.println("\n=== 게시글 목록 조회 ===\n");

        try {
            // 게시글 목록 조회 서비스 호출
            List<Board> boardList = service.selectAllBoard();

            if(boardList.isEmpty()) {
                System.out.println("\n*** 게시글이 존재하지 않습니다 ***\n");
                return;
            }

            for(Board board : boardList) {
                System.out.printf("%d | %s[%d] | %s | %s | %d\n",
                        board.getBoardNo(),
                        board.getBoardTitle(),
                        board.getCommentCount(),
                        board.getMemberName(),
                        board.getCreateDate(),
                        board.getReadCount());
            }
        } catch(Exception e) {
            System.out.println("\n*** 게시글 목록 조회 중 예외 발생 ***\n");
            e.printStackTrace();
        }
    }
}

package edu.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.board.model.dto.Comment;
import edu.kh.jdbc.board.model.service.CommentService;
import edu.kh.jdbc.common.Session;

public class CommentView {
	private CommentService service = new CommentService();
	private Scanner sc = new Scanner(System.in);

	/**
	 * 댓글 메뉴
	 * @param boardNo(상세 조회 중인 게시글 번호)
	 */
	public void commentMenu(int boardNo) {

		int input = -1;

		try {
			System.out.println("\n=== 댓글 기능 ===\n");
			System.out.println("1) 댓글 등록");
			System.out.println("2) 댓글 수정");
			System.out.println("3) 댓글 삭제");
			System.out.println("0) 댓글 기능 종료");

			System.out.print("\n선택 : ");
			input = sc.nextInt(); sc.nextLine();

			switch (input) {
			// !wq가 입려될 때 까지 댓글 내용을 입력받고 
			// 내용, 게시글 번호, 로그인 회원 번호를 이용해 댓글 삽입 서비스 호출 
	        case 1: insertComment(boardNo); break; // 댓글 등록
			
			// 댓글 번호를 입력 받아
			// 1) 해당 댓글이 현재 게시글의 댓글이며 
	        //    현재 로그인한 회원이 쓴 댓글이 맞는지 확인하는 서비스 호출
	        // 2-1) 1번 결과가 맞지 않으면 "작성한 댓글만 수정할 수 있습니다" 출력
	        // 2-2) !wq가 입력될 때 까지 내용 입력 후 댓글번호, 내용을 이용해 댓글 수정 서비스 호출
	        case 2: updateComment(boardNo); break; // 댓글 수정
	        
	        // 댓글 번호를 입력 받아
 			// 1) 해당 댓글이 현재 게시글의 댓글이며 
 	        //    현재 로그인한 회원이 쓴 댓글이 맞는지 확인하는 서비스 호출
	        // 2-1) 1번 결과가 맞지 않으면 "작성한 댓글만 삭제할 수 있습니다" 출력
	        // 2-2) 맞으면 "정말로 삭제하시겠습니까?(Y/N) : " 출력 후
	        //      Y 입력 시 삭제 서비스 호출(댓글번호)
	        //      N 입력 시 "취소되었습니다"
	        case 3: deleteComment(boardNo); break; // 댓글 삭제
			case 0: return;
			default: System.out.println("\n*** 메뉴 번호만 입력 해주세요 ***\n");
			}
		} catch (InputMismatchException e) {
			System.out.println("\n*** 입력 형식이 올바르지 않습니다***\n");
			sc.nextLine();
		}
	}

	/**
	 * 댓글 삭제
	 * @param boardNo
	 */
	private void deleteComment(int boardNo) {
		System.out.println("\n=== 댓글 삭제 ===\n");
		
		System.out.print("삭제할 댓글 번호 입력 : ");
		int commentNo = sc.nextInt(); sc.nextLine();
		
		try {
			Comment comment = service.selectComment(commentNo);
			if(comment == null) {
				System.out.println("\n=== 해당하는 댓글이 존재하지 않습니다 ===\n"); return;
			} if(comment.getBoardNo() != boardNo) {
				System.out.println("\n=== 해당 게시글 댓글만 삭제할 수 있습니다 ===\n"); return;
			} if(comment.getMemberNo() != Session.loginMember.getMemberNo()) {
				System.out.println("\n=== 작성한 댓글만 삭제할 수 있습니다 ===\n"); return;
			}
			
			while(true) {
				System.out.print("정말로 삭제하시겠습니까?(Y/N) : ");
				char check = sc.next().toUpperCase().charAt(0);
				if(check == 'N') {
	                System.out.println("취소되었습니다");
	                return;
	            } else if(check != 'Y') {
	                System.out.println("잘못 입력하셨습니다");
	                continue;
	            }
	            break; // check == 'Y' 인 경우
			}
			
	        int result = service.deleteComment(commentNo);
	        if(result > 0) System.out.println("\n=== 댓글이 삭제되었습니다 ===\n");
			else 		   System.out.println("\n*** 댓글 삭제 실패 ***\n");
		} catch (Exception e) {
			System.out.println("\n*** 삭제 수정 중 오류가 발생했습니다 ***\n");
			e.printStackTrace();
		}
	}

	/**
	 * 댓글 수정
	 * @param boardNo
	 */
	private void updateComment(int boardNo) {
		System.out.println("\n=== 댓글 수정 ===\n");
		
		System.out.print("수정할 댓글 번호 입력 : ");
		int commentNo = sc.nextInt(); sc.nextLine();
		
		try {
			Comment comment = service.selectComment(commentNo);
			if(comment == null) {
				System.out.println("\n=== 해당하는 댓글이 존재하지 않습니다 ===\n"); return;
			} if(comment.getBoardNo() != boardNo) {
				System.out.println("\n=== 해당 게시글 댓글만 수정할 수 있습니다 ===\n"); return;
			} if(comment.getMemberNo() != Session.loginMember.getMemberNo()) {
				System.out.println("\n=== 작성한 댓글만 수정할 수 있습니다 ===\n"); return;
			}
			
			// 특정 단어(!wq)가 입력될 때 까지 무한 입력
			System.out.println("수정할 댓글 내용 입력<!wq 입력 시 종료> : ");
	        StringBuffer contentBuffer = new StringBuffer();
	        while(true) {
	            String str = sc.nextLine();
	            if(str.equals("!wq")) break;
	            contentBuffer.append(str).append("\n");
	        }
	        
	        int result = service.updateComment(commentNo, contentBuffer.toString());
	        if(result > 0) System.out.println("\n=== 댓글이 수정되었습니다 ===\n");
			else 		   System.out.println("\n*** 댓글 수정 실패 ***\n");
		} catch (Exception e) {
			System.out.println("\n*** 댓글 수정 중 오류가 발생했습니다 ***\n");
			e.printStackTrace();
		}
	}

	/**
	 * 댓글 등록 
	 * @param boardNo
	 */
	private void insertComment(int boardNo) {
		System.out.println("\n=== 댓글 등록 ===\n");
		
		StringBuffer contentBuffer = new StringBuffer();
		System.out.println("댓글 입력 : ");
		while(true) {
			String str = sc.nextLine();
			if(str.equals("!wq")) break;
			contentBuffer.append(str).append("\n");
		}
		
		try {
			int result = service.insertComment(Session.loginMember.getMemberNo(), boardNo, contentBuffer.toString());
			if(result > 0) System.out.println("\n=== 댓글이 작성되었습니다 ===\n");
			else 		   System.out.println("\n*** 댓글 작성 실패 ***\n");
		} catch (Exception e) {
			System.out.println("\n*** 댓글 작성 중 오류가 발생했습니다 ***\n");
			e.printStackTrace();
		}
	}
}

package edu.kh.project.board.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.sevice.BoardService;
import edu.kh.project.member.model.dto.Member;

@SessionAttributes({"loginMember"})
@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	/* 목록 조회 : /board/1?cp=1 (cp: current page, 현재페이지)
	 * 상세 조회 : /board/1/1500?cp=1
	 * 
	 * ** 컨트롤러 따로 생성 예정 **
	 * 삽입 : /board2/insert?code=1 (code: BOARD_CODE, 게시판 종류)
	 * 수정 : /board2/update?code=1&no=1500 (no: BOARD_NO, 게시글 번호)
	 * 삭제 : /board2/delete?code=1&no=1500
	 * */
	
	
	// @PathVariable
	// URL 경로에 있는 값을 매개변수로 이용할 수 있게 하는 어노테이션
	// + Request Scope에 setting 해줌
	
	// @PathVariable 을 사용하는 경우
	// - 자원(resource) 구분/식별
	// ex. github.com/username
	// ex. /board/1 => 1번(공지사항) 게시판
	//     /board/2 => 2번(자유게시판) 게시판
	
	// Query String을 사용하는 경우
	// - 정렬, 필터링
	// ex. search.naver.com?query=날씨
	// ex. /board2/insert?code=1
	//     /board2/insert?code=2
	//     => '삽입'이라는 공통된 동작을 수행하지만 code에 따라 어디에 삽입할지 결정(필터링)
	// ex. /board/list?order=recent (최신순)
	// ex. /board/list?order=most (인기순)
	
	
	// @PathVariabel 사용 시 문제점과 해결 방법
	// 문제점 : 요청 주소와 @PathVariable로 가져다 쓸 주소의 레벨이 같으면
	//          내용을 구분하지 않고 모두 Mapping 되는 문제 발생
	// => 요청을 했는데 원하는 메서드가 실행 안됨
	// 해결방법 : @PathVariable 지정 시 정규 표현식 사용
	// => {키:정규표현식}
	
	// 게시글 목록 조회
	@GetMapping("/{boardCode:[0-9]+}") // boardCode는 1자리 이상 숫자
	public String selectBoardList(
			@PathVariable("boardCode") int boardCode,
			@RequestParam(value="cp", required=false, defaultValue="1") int cp,
			Model model) {
		
		// boardCode 확인
//		System.out.println("boardCode : " + boardCode);
		
		// 게시글 목록 조회 서비스 호출
		Map<String, Object> map = service.selectBoardList(boardCode, cp); 
		
		// 조회 결과를 request scope에 세팅 후 forward
		model.addAttribute("map", map);
		return "board/boardList";
	}
	
	// 게시글 상세조회
	// @PathVariable : 주소에 지정된 부분을 변수에 저장 + 자동으로 request scope의 변수로 저장됨
	@GetMapping("/{boardCode}/{boardNo}")
	public String boardDetail(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			Model model,
			RedirectAttributes ra, // 리다이렉트 시 데이터 전달용 객체
			@SessionAttribute(value="loginMember", required=false) Member loginMember,
			// Session에서 loginMember를 얻어오는데 없으면 null, 있으면 회원정보 저장
			HttpServletRequest req, HttpServletResponse resp // 쿠키를 이용한 조회 수 증가에서 사용
			) throws ParseException { 
		
		// 게시글 상세 조회 서비스 호출
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		Board board = service.selectBoard(map);

		String path;
		if(board != null) { // 조회 결과가 있을 경우
			
			//-------------------------------------------------------------------------------------
			// 현재 로그인 상태인 경우 로그인한 회원이 해당 게시글에 좋아요를 눌렀는지 확인
			//-------------------------------------------------------------------------------------
			if(loginMember != null) { // 로그인 상태인 경우
				
				// 회원 번호를 map에 추가
				map.put("memberNo", loginMember.getMemberNo());
				// => map(boardCode, boardNo, memberNo)
				
				// 좋아요 여부 확인 서비스 호출
				int result = service.boardLikeCheck(map);
				
				// 누른적이 있을 경우
				if(result > 0) model.addAttribute("likeCheck", "on");
			}
			//-------------------------------------------------------------------------------------
			
			
			
			//-------------------------------------------------------------------------------------
			// 쿠키를 이용한 조회수 증가 처리
			//-------------------------------------------------------------------------------------
			
			// 1) 비회원 또는 로그인한 회원의 글이 아닌 경우 => 조회수 증가
			if(loginMember == null || loginMember.getMemberNo() != board.getMemberNo()) {
				
				// 2) 쿠키 얻어오기
				Cookie c = null;
				Cookie[] cookies = req.getCookies(); // 요청에 담겨있는 모든 쿠키 얻어오기
				if(cookies != null) { // 쿠키가 1개 이상 존재할 경우
					// 쿠키 중 "readBoardNo"라는 쿠키를 찾아서 c에 대입
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("readBoardNo")) {
							c = cookie;
							break;
						}
					}
				}
				
				// 3) 기존 쿠키가 없거나(c == null) 
				//    존재는 하지만 현재 게시글 번호가 쿠키에 저장되지 않은 경우(오늘 해당 게시글 본 적 없음)
				int result = 0;
				if(c == null) { // 쿠키가 존재하지 않음
					// 쿠키 생성
					// cf. tomcat 8.5 이상부터 쿠키에 ,/= (공백) 작성 불가
					c = new Cookie("readBoardNo", "|" + boardNo + "|");
					
					// 조회수 증가 서비스 호출
					result = service.updateReadCount(boardNo);
				} else {
					// 현재 게시글 번호가 쿠키에 있는지 확인
					// Cookie.getValue() : 쿠키에 저장된 모든 값을 읽어옴 -> String으로 반환
					// String.indexOf("문자열") : 찾는 문자열이 String 몇 번째 인덱스에 존재하는지 반환
					//                            단, 없을 시 -1 반환
					if(c.getValue().indexOf("|" + boardNo + "|") == -1) { // 쿠키에 현재 게시글 번호가 없으면
						// 기존 값에 현재 게시글 번호를 추가해서 쿠키에 다시 셋팅
						c.setValue(c.getValue() + "|" + boardNo + "|");
						result = service.updateReadCount(boardNo);
					}
				} // 3) 종료
				
				// 4) 조회 수 증가 성공 시 
				// 쿠키가 적용되는 경로, 수명(당일 23:59:59) 지정
				if(result > 0) {
					
					// 조회수 DB와 동기화
					board.setReadCount(board.getReadCount() + 1);
					
					// 쿠키 적용경로 설정
					c.setPath("/"); // "/" 이하 경로 요청 시 쿠키 서버로 전달
					
					// 쿠키 수명 지정
					Calendar cal = Calendar.getInstance(); // 싱글톤 패턴, 오늘 날짜
					cal.add(cal.DATE, 1); // 오늘 날짜 + 1
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 표기법 변경 객체
					
					// java.util.Date
					Date a = new Date(); // 현재 시간
					Date temp = new Date(cal.getTimeInMillis()); // 내일(24시간 후)
					Date b = sdf.parse(sdf.format(temp)); // 내일(00:00:00)
					
					// 내일 00:00:00 - 현재 시간
					long diff = (b.getTime() - a.getTime()) / 1000; // ms / 1000 = sec
					// => 내일 00:00:00 까지 남은 시간 초단위 반환
					
					c.setMaxAge((int)diff); // 수명 설정
					
					// 응답 객체를 이용해서 클라이언트에게 전달
					resp.addCookie(c);
				}
			}
			//-------------------------------------------------------------------------------------
			
			
			path = "board/boardDetail";
			model.addAttribute("board", board);
		} else { // 조회 결과가 없을 경우
			// 게시판 첫 페이지로 리다이렉트
			path = "redirect:/board/" + boardCode;
			ra.addFlashAttribute("message", "해당 게시글이 존재하지 않습니다.");
		}
		
		return path;
	}
	
	// 좋아요 처리
	@PostMapping("/like")
	@ResponseBody // 반환되는 값이 비동기 요청한 곳으로 돌아가게 함
	public int like(@RequestBody Map<String, Integer> paramMap) {
//		System.out.println(paramMap);
		return service.like(paramMap);
	}
}

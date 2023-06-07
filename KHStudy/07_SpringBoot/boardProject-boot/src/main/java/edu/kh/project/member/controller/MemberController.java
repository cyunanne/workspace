package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	/**
	 * 로그인 요청 처리
	 * @return 메인페이지 redirect 주소
	 */
	@PostMapping("/login")
	public String login(
			Member inputMember,
			Model model, 
			@RequestHeader(value="referer") String referer,
			@RequestParam(value="saveId", required=false) String saveId,
			HttpServletResponse resp,
			RedirectAttributes ra) {
		
		Member loginMember = service.login(inputMember);
		
		String path = "redirect:";
		if(loginMember != null) { // 로그인 성공
			path += "/";
			model.addAttribute("loginMember", loginMember); // request scope
			// 2) class 위에 @SessionAttributes 추가 -> session scope로 변경됨
			
			//////////////////////////////////아이디 저장///////////////////////////////////
			
			/* Cookie란?
             * - 클라이언트 측(브라우저)에서 관리하는 파일
             * - 쿠키파일에 등록된 주소 요청 시 마다 자동으로 요청에 첨부되어 서버로 전달됨.
             * - 서버로 전달된 쿠키에 값 추가, 수정, 삭제 등을 진행한 후 다시 클라이언트에게 반환
             * cf. Session : 서버가 클라이언트의 정보를 저장(쿠키와의 차이점)
			 */
			
			// 쿠키 생성(해당 쿠기에 담을 데이터를 k:v로 지정)
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) { // 체크 되었을 때
				// 한 달(30일) 동안 유지되는 쿠키 생성
				cookie.setMaxAge(60*60*24*30); // s단위
			} else { // 체크 안되었을 때
				// 0초 동안 유지되는 쿠키 생성
				// => 기존 쿠키를 삭제
				cookie.setMaxAge(0);
			}
			
			// 클라이언트가 쿠키를 첨부할 요청 경로 지정
			cookie.setPath("/"); // "localhost/" 이하 모든 주소
			
			// 응답 객체(HttpServletResponse)를 이용해 쿠키를 클라이언트에게 전달
			resp.addCookie(cookie);
			
			////////////////////////////////////////////////////////////////////////////////
			
		} else  { // 로그인 실패
			path += referer;
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		return path;
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(SessionStatus status, HttpSession session) {
		status.setComplete(); // session clear
		
		return "redirect:/";
	}
	
}

package edu.kh.project.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	
	// 로그인
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 인코딩 처리
		
		try {
			// 입력된 값(파라미터) 얻어오기
			String inputEmail = req.getParameter("inputEmail");
			String inputPw = req.getParameter("inputPw");
			
			// 서비스 객체 생성
			MemberService service = new MemberService();
			
			// 로그인 서비스 호출 후 결과 반환 받기
			Member loginMember = service.login(inputEmail, inputPw);
			
			// Session 객체 생성
			HttpSession session = req.getSession();
			if(loginMember != null) { // 로그인 성공
				// session에 로그인한 회원 정보 추가
				session.setAttribute("loginMember", loginMember);
				// session 만료 시간 지정(초 단위)
				session.setMaxInactiveInterval(3600); // 테스트 후 1시간으로 변경
				
				/* ******* 요청 주소(주소창에 적는 주소) 작성 ********* */
				// forward  : 요청 처리 후 자체적인 화면(JSP)을 이용해 응답 
				// redirect : 요청을 처리 후 보여줄 화면이 없고, 다른 서비스를 요청 *지금 하는 것
				resp.sendRedirect("/");
			} else {
				// 로그인 실패 메시지를 session에 추가
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
				
				// 현재 요청 이전 페이지로 redirect
				String referer = req.getHeader("referer");
				resp.sendRedirect(referer);
			}
			
			System.out.println(loginMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

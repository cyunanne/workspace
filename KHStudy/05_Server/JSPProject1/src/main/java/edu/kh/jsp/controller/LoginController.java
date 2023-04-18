package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login") // 현재 클래스를 Servlet 등록 + /login 요청을 처리
public class LoginController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// POST 방식의 문제점 : 한글이 깨진다(인코딩 불일치 문제)
		req.setCharacterEncoding("UTF-8"); // 요청 데이터 인코딩 변경
		
		// 전달받은 값(파라미터) 얻어와서 변수에 저장
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		// id/pw가 user01/pass01과 일치하면 "로그인 성공"
		// 일치하지 않으면 "아이디 또는 비밀번호가 일치하지 않습니다"
		String msg = null;
		if( id.equals("user01") && pw.equals("pass01") ) msg = "로그인 성공";
		else msg = "아이디 또는 비밀번호가 일치하지 않습니다";
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Servlet으로 클라이언트에게 응답할 화면 만들기
		// => 작성하기 힘드니까 JSP로 위임
		
		// RequestDispatcher dispatcher = req.getRequestDispatcher("요청을 위임할 JSP 경로");
		// - RequestDispatcher : 요청 정보, 응답하는 역할을 넘기는 객체(Dispatcher : 발송자, 정보 제공자)
		// - JSP 경로 작성 방법 : root폴더=webapp
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp");
		
		// ----------------------------------------------------------------------
		// 기존 req에 없던 값(msg)을 속성(Attribute)으로 추가
		// -> map 형식으로 작성
		   req.setAttribute("message", msg);
		// ----------------------------------------------------------------------
		
		// *****************************
		// ******* F O R W O R D *******
		// *****************************
		// 뜻 : 전송하다, 전달하다, 보내다
		// -> 서블릿이 가지고있던 HttpServletRequest, HttpServletResponse 객체를 JSP에게 전달하여 요청을 위임
		// forward를 하면 url이 요청 주소로 바뀌고, 그 요청에 맞는 화면으로 변함(요청에 맞는 응답을 했기 때문)
		dispatcher.forward(req, resp);
		
		// *******************************
		// ******* R E D I R E C T *******
		// *******************************
		// 뜻 : 다시 다른 요청을 함(재요청)
		// -> 현재 요청("/login")에 대한 응답 화면이 존재하지 않아 응답화면이 있는 다른 요청 주소("/")로 다시 요청
   	    // "/" 요청에 대한 forward 처리 = index.html
//		resp.sendRedirect("/"); // 메인페이지를 다시 요청
	}
}

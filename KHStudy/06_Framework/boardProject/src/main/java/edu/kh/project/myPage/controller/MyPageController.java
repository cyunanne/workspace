package edu.kh.project.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.myPage.model.service.MyPageService;

// @SessionAttributes
// 1) Model에 세팅된 값의 key와 {}에 작성된 값이 일치하면 session scope로 이동
// 2)
@SessionAttributes({"loginMember"})
@RequestMapping("/myPage") // /myPage로 시작하는 요청을 모두 받음
@Controller // 요청/응답 제어 클래스 명시 + Bean 등록(IoC)
public class MyPageController {
	@Autowired // MyPageService 자식 MyPageServiceImple 의존성 주입(DI)
	private MyPageService service;
	
	@GetMapping("/info")
	public String info() {
		// ViewResolver 설정 -> servlet-context.xml
		return "/myPage/myPage-info";
	}
	
	@GetMapping("/profile")
	public String profile() {
		// ViewResolver 설정 -> servlet-context.xml
		return "/myPage/myPage-profile";
	}
	
	@GetMapping("/changePw")
	public String changePw() {
		// ViewResolver 설정 -> servlet-context.xml
		return "/myPage/myPage-changePw";
	}
	
	@GetMapping("/secession")
	public String secession() {
		// ViewResolver 설정 -> servlet-context.xml
		return "/myPage/myPage-secession";
	}
}

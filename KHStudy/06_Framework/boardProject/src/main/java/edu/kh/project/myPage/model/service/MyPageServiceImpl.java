package edu.kh.project.myPage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.myPage.model.dao.MyPageDAO;

@Service // 비즈니스 로직 처리 클래스 의미 + Bean 등록(IoC)
public class MyPageServiceImpl implements MyPageService {
	@Autowired // MyPageDAO 의존성 주입(DI)
	private MyPageDAO dao;
	
	
}

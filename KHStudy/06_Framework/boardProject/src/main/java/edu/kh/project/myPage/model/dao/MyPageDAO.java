package edu.kh.project.myPage.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 저장소(DB)와 관련된 클래스 + Bean 등록 (IoC, 스프링이 객체 관리)
public class MyPageDAO {
	// 등록된 Bean 중 타입이 SqlSessionTemplate과 일치하는 Bean을 주입(DI, 스프링에 의한 의존성 주입)
	// -> root-context.xml에 <bean> 작성됨
	@Autowired
	private SqlSessionTemplate sqlSession;
}

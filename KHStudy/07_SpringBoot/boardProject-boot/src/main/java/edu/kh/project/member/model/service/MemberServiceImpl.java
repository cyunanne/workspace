package edu.kh.project.member.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.MemberMapper;
import edu.kh.project.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired
	private MemberMapper dao;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	// CheckedException이었던 SQLException이 Spring에서는 UncheckedException이 된다 
	public Member login(Member inputMember) {
		
		// 로그 출력
		logger.info("MemberService.login() 실행"); // 정보 출력
		logger.debug("memberEmail : " + inputMember.getMemberEmail());
		logger.warn("이건 경고용도");
		logger.error("이건 오류 발생 시");
	
		Member loginMember = dao.login(inputMember);
		
		if(loginMember != null) {
			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) { // 같을 경우
				loginMember.setMemberPw(null);
			} else { // 다를 경우
				loginMember = null; // 로그인 실패하게 만듦
			}
		}
		
		return loginMember;
	}

	
	// 회원 가입 서비스
	@Override
	@Transactional(rollbackFor = {Exception.class})
	// @Transactional(rollbackFor = {Exception.class})
	// 예외가 발생하면 rollback
	// 발생하지 않으면 Service 종료 시 commit
	public int signUp(Member inputMember) {
		// 비밀번호 BCrypt를 이용하여 암호화 후 다시 inputMember.setPw()
		String encPw = bcrypt.encode(inputMember.getMemberPw());
		inputMember.setMemberPw(encPw);
		
		// DAO 호출
		int result = dao.signUp(inputMember);
		
		return result;
	}
}

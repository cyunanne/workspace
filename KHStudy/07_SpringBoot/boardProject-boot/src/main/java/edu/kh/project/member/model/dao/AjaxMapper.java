package edu.kh.project.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface AjaxMapper {
	
	/**
	 * 이메일로 닉네임 조회
	 * @param email
	 * @return memberNickname
	 */
	String selectNickname(String email);
	
	/**
	 * 닉네임으로 전화번호 조회
	 * @param nickname
	 * @return memberTel
	 */
	String selectMemberTel(String nickname);
	
	/**
	 * 이메일 중복 확인
	 * @param email
	 * @return 1 or 0
	 */
	int checkEmail(String email);
	
	/**
	 * 닉네임 중복 확인
	 * @param nickname
	 * @return 1 or 0
	 */
	int checkNickname(String nickname);
	
	/**
	 * 이메일로 회원 정보 조회
	 * @param email
	 * @return member
	 */
	Member selectMember(String email);
	
	/**
	 * 이메일이 일부라도 일치하는 회원 목록 조회
	 * @param input
	 * @return memberList
	 */
	List<Member> selectMemberList(String input);
}

package edu.kh.project.myPage.model.dao;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface MyPageMapper {

	/**
	 * 회원 정보 수정
	 * @param updateMember
	 * @return result
	 */
	public int updateInfo(Member updateMember);

	/**
	 * 회원 비밀번호 조회
	 * @param memberNo
	 * @return encPw
	 */
	public String selectEncPw(int memberNo);

	/**
	 * 비밀번호 변경
	 * @param member
	 * @return result
	 */
	public int changePw(Member member);

	/**
	 * 회원 탈퇴
	 * @param memberNo
	 * @return
	 */
	public int secession(int memberNo);

	/**
	 * 프로필 이미지 수정
	 * @param loginMember
	 * @return result
	 */
	public int updateProfileImage(Member loginMember);
}

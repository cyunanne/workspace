package edu.kh.api.dto;

public class Student {

	private int grade;
	private int classRoom;
	private int number;
	private String name;
	
	public Student() {}
	
	public Student(int grade, int classRoom, int number, String name) {
		this.grade = grade;
		this.classRoom = classRoom;
		this.number = number;
		this.name = name;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public int getClassRoom() {
		return classRoom;
	}
	
	public void setClassRoom(int classRoom) {
		this.classRoom = classRoom;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("%d학년 %d반 %d번 %s", grade, classRoom, number, name);
	}
	
	// Object.equals() 오버라이딩
	// 현재 객체와 다른 객체가 동등(필드값이 같은지) 비교
	// 필드를 가지고 있는 자식 클래스에 알맞은 형태로 재정의(오버라이딩) 필요
	@Override
	public boolean equals(Object obj) {
		
		/* 사전 비교 */
		// 비교 대상이 자기 자신일 때(같은 객체를 잠조할 경우)
		if( obj == this ) return true;
		
		// null인 경우 비교 자체가 불필요
		if( obj == null ) return false;
		
		// 비교를 위해 전달받은 객체가 Student가 맞는가? -> false인 경우 실행
		if(!(obj instanceof Student)) return false;
		
		
		/* 필드 비교 */
		// 1. Object 클래스 다운캐스팅
		Student other = (Student)obj;
		// 2. 필드 각각 비교
		if( this.grade != other.grade ) return false;
		if( this.classRoom != other.classRoom ) return false;
		if( this.number != other.number ) return false;
		if( !this.name.equals(other.name) ) return false;
		
		return true;
	}
	
	/*
	 * Object.hashCode();
	 * - 두 객체의 필드 값이 같다면 hashCode()도 똑같은 정수 값을 반환해야 한다.
	 * - hash 함수: 입력받은 문자열/숫자를 특정한 길이의 문자열/숫자로 반환
	 *              => 최대한 중복되지 않는 숫자를 만들어냄
	 * - hashCode(): 객체의 필드 값을 이용해서 일정한 길이의 숫자를 만드는 함수
	 * - 사용이유: 자바 프로그램 실행 시 내부에서 객체 검색 속도 증가
	 * - 작성방법: 필드 값이 같으면 항상 같은 수가 나올 수 있도록 구현 
	 *			   + equals() 오버라이딩 시 필수적으로 같이 오버라이딩
	 */
	@Override
	public int hashCode() {
		int result = 1;
		final int PRIME = 31; // 소수 (31: 곱연산 시 속도 빠른 숫자임..)
		
		result = result * PRIME + grade;
		result = result * PRIME + classRoom;
		result = result * PRIME + number;
		
		result = result * PRIME + ( name==null ? 0 : name.hashCode());
		
		return result;
	}
}

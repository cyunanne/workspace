package edu.kh.objectarray.dto;

public class Student {

	// 필드(멤버변수), 캡슐화 원칙에 의해 모두 private화
	private int grade;
	private int classRoom;
	private int number;
	private String name;

	private int kor;
	private int eng;
	private int math;

	/* 생성자 */
	// 기본 생성자
	public Student() {} 

	// 매개변수 생성자
	public Student(int grade, int classRoom, int number, String name) { // 매개변수 생성자(오버로딩 적용)
		// this 참조변수
		this.grade = grade; // 매개변수로 전달받은 값을, 필드에 있는 그레이드 값에 대입해라
		this.classRoom = classRoom;
		this.number = number;
		this.name = name;
	}

	// getter
	// public 반환형 get필드명() { return 필드명; }
	public int getGrade() {
		return grade;
	}

	public int getClassroom() {
		return classRoom;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}
	
	public int getKor() {
		return kor;
	}

	public int getEng() {
		return eng;
	}

	public int getMath() {
		return math;
	}

	// setter
	// public 반환형 set필드명() {} 
	// 반환할 값이 없음. 셋팅하면 끝 그래서 return값 없이 void
	public void setGrade(int grade) { 
		this.grade = grade;
	}

	public void setClassroom(int classroom) {
		this.classRoom = classroom;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public void setMath(int math) {
		this.math = math;
	}

	// 객체의 필드 값을 하나의 문자열 형태로 반환하는 메서드
	// 자바에서 String은 class임, format은 printf같은 기능
	public String toString() {
		return String.format("%d학년 %d반 %d번 %s [%d, %d, %d]", grade, classRoom, number, name, kor, eng, math); 
	}
	
	public void updateScore(int kor, int eng, int math) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

}
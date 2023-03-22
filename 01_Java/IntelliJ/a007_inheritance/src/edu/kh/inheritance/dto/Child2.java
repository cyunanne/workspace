package edu.kh.inheritance.dto;

public class Child2 extends Parent {
	
	private String house;

	public Child2() {
		System.out.println("Child2() 기본 생성자");
	}

	public Child2(String house) {
		this.house = house;
		System.out.println("Child2(String) 매개변수 생성자");
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	// 자식이 상속받은 getMoney()을 재정의
	@Override // 컴파일러에게 해당 메소드가 재정의 되었음을 알려주는 컴퓨터용 주석 -> 오버라이딩 형식이 맞는지 검사 진행
	public int getMoney() {
		System.out.println("자식이 오버라이딩 한 getMoney()");
		return super.getMoney() + 500;
	}

	public String toString() {
		return house;
	}
}

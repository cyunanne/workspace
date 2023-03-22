package edu.kh.inheritance.dto;

public class Child1 extends Parent {
	
	private String car;
	
	// 기본 생성자
	public Child1() {
		super(); // 부모 클래스 기본생성자
		System.out.println("Child1() 기본 생성자");
	}

	// 매개변수 생성자
	public Child1(String car) {
		super(800_000_000, "송"); // 부모 클래스 매개변수 생성
		this.car = car;
		System.out.printf("Child1(String:%s) 매개변수 생성자\n", car);
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}
	
	public String toString() {

		// 자신의(같은 클래스) 메서드 호출 시 이름만 부르면 된다.
		// + 상속 특징(부모 필드/메서드를 상속받아 자식 것 처럼 사용)
//		System.out.println(getCar());
//		System.out.println(getMoney());
//		System.out.println(getLastName());

//		return car + " / " + getMoney() + " / " + getLastName(); // -------> 문제점 : 코드 중복
//		return car + "/ " + toString(); 						 // -------> !! StackOverflowError 발생 !!
//																	  	     문제점 : Child1의 toString() 반복 호출(재귀호출)
//						 											  	     해결방법 : 부모의 toString() 호출 명시(super.)
		return super.toString() + " / " + car;
	}
}

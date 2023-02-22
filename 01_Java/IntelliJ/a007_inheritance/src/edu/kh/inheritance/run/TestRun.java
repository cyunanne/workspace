package edu.kh.inheritance.run;

import edu.kh.inheritance.dto.Child1;
import edu.kh.inheritance.dto.Child2;
import edu.kh.inheritance.dto.Parent;

public class TestRun {

	public static void main(String[] args) {
		
		// 부모, 자식1, 자식2 객체 생성
//		Parent p = new Parent(1_000_000_000, "김");
		Child1 c1 = new Child1("아반떼");
		Child2 c2 = new Child2("반포 자이");
		
		// 상속(extends)
		// : 부모 클래스가 가지고있는 필드, 메서드를 자식 클래스가 자신의 것처럼 사용 가능하게하는 기술
		System.out.println(c1.getMoney());
		System.out.println(c1.getLastName());

		// 메서드 오버라이딩 시 자식이 우선순위를 가지게 된다.
		System.out.println(c2.getMoney());
		System.out.println(c2.getLastName());
		// => 부모의 메서드 상속 확인
		
//		System.out.println(c1.money);
		// => 부모의 private 접근 제한자는 상속을 받아도 직접 접근 불가능(상속은 되어 있으나 접근은 불가)
		
		// 부모 클래스의 코드를 수정하면 자식 모두에게 적용된(공통적인 규약, 유지보수성 향상)


		// toString() : 객체가 가지고있는 필드값을 하나의 문자열로 만들어서 반환
		System.out.println("--------------------------");
		System.out.println(c1.toString());
	}
}

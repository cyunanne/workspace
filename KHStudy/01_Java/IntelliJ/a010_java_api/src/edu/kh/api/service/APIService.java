package edu.kh.api.service;

import edu.kh.api.dto.Student;

/*
 * API(Application Programming Interface)
 * : 프로그래밍 언어가 이미 가지고 있는 클래스/기능/기술 등을 사용자가 쉽게 사용할 수 있도록 제공하는 것
 * */

public class APIService {
	
	private Student[] studentList = new Student[10];
	
	public APIService() {
		studentList[0] = new Student(1, 1, 1, "김뫄뫄");
		studentList[1] = new Student(2, 3, 4, "홍길동");
		studentList[2] = new Student(3, 5, 12, "박민지");
	}
	
	/**
	 * 학생 추가 서비스
	 * @param grade
	 * @param classRoom
	 * @param number
	 * @param name
	 * @return 추가 성공시 true / 실패시 false
	 */
	public boolean addStudent(int grade, int classRoom, int number, String name) {
		
		int index = 0;
		for( Student s : studentList) {
			if( s == null ) break;
			
			boolean check1 = s.getGrade() == grade;
			boolean check2 = s.getClassRoom() == classRoom;
			boolean check3 = s.getNumber() == number;
			boolean check4 = s.getName().equals(name);
			
			// 학생 배열에 입력받은 학생이 존재한다면 false 반환
			if( check1 && check2 && check3 && check4 ) {
				return false;
			}
			index++;
		}
		
		// 학생 배열이 꽉 차있다면 false 반환
		if(index == studentList.length) return false;
		
		// 지정된 index에 학생 추가
		studentList[index] = new Student(grade, classRoom, number, name);
		return true;
	}

	/**
	 * 학생 추가 서비스2 (equals() + hashCode() 사용)
	 * @param grade
	 * @param classRoom
	 * @param number
	 * @param name
	 * @return 추가 성공시 true / 실패시 false
	 */
	public boolean addStudent2(int grade, int classRoom, int number, String name) {
		
		// 1. equals()를 이용한 비교
		//   1) 전달받은 값으로 임시 학생 객체 생성
		Student tmp = new Student(grade, classRoom, number, name);
		//   2) 학생 배열을 순차 접근 하면서 동등한 학생이 있는지 검사
		int index = 0;
		for( Student s : studentList) {
			if( s == null ) break;
			
			// 1. equals()
			// s와 tmp가 동등한 경우(학생 배열에 입력받은 학생이 존재한다면) false 반환
//			if( s.equals(tmp) ) return false;
						
			// 2. hashCode()
			// hashCode()가 같다 == 필드가 같다 == 중복되는 학생이다
			if(s.hashCode() == tmp.hashCode()) return false;
			
			index++;
		}
		
		//   3) 학생 배열이 꽉 차있다면 false 반환
		if(index == studentList.length) return false;
		
		//   4) 지정된 index에 학생 추가
		studentList[index] = tmp;
		return true;
	}
	
	/**
	 * 학생 배열에서 이름 검색
	 * @param input-String, 검색 대상
	 * @return 일치하는 학생이 있으면 Student[] / 없으면 null 반환
	 */
	public Student[] selectname(String input) {
				
		String[] names = input.split(",|/| "); // String.split(): 문자열을 구분자를 기준으로 나누어 String[] 형태로 반환
		Student[] result = new Student[studentList.length]; // 검색된 학생 저장
		int index = 0; // result 배열에 저장될 위치를 지정할 변수
		
		for(String n : names) {
			for(Student s : studentList) {
				if(s == null) break;
				
				// 입력받은 이름 == 학생 이름
				if(s.getName().equals(n)) {
					result[index++] = s; // result 배열에 일치하는 학생 저장(얕은 복사)
				}
			}
		}
		
		if( index == 0) return null;
		
		return result;
	}

	/**
	 * 모든 학생 이름을 하나의 문자열로 반환
	 * @return 
	 */
	public String printName() {
		
		int size=0;
		for(Student s : studentList) {
			if(s == null) break;
			size++;
		}
		
		String[] names = new String[size];
		for(int i=0; i<size; i++) 
			names[i] = studentList[i].getName();
		
		// String.join("구분자", String[]);
		// : String[]의 요소 사이사이에 "구분자" 추가하여 하나의 문자열로 합침 
		return String.join(", ", names); 
	}
}

package edu.kh.objectarray.run;

import edu.kh.objectarray.view.StudentView;

/**
 * 자바 프로그램 실행 순서
 * 1. static 읽기
 * 2. main() 실행
 * 3. StudentView 객체 실행
 * 4. Scanner, StudentService 객체 생성
 * 5. Student[5] 배열 생성
 *    -> 기본 생성자 내용 수행(Student 객체 3개 생성 후 0, 1, 2 인덱스에 참조값 대입)
 */

public class StudentRun {	

	public static void main(String[] args) {
		
		StudentView view = new StudentView();
		view.displayMenu();
	}
}

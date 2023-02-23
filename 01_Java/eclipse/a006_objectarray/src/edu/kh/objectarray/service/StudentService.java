package edu.kh.objectarray.service;

import java.util.Random;

import edu.kh.objectarray.dto.Student;

// 기능 제공용 클래스(비즈니스 로직 처리)
public class StudentService {

	// Student 객체 참조변수 5개짜리 배열을 생성해서 studentArr에 참조값 전달
	private Student[] studentArr = new Student[5];

	// StudentService 기본 생성자
	public StudentService() {

		// studentArr 배열의 각 요소는 Student 참조 변수
		studentArr[0] = new Student(3, 5, 17, "홍길동"); //studentArr[0].updateScore(10, 20, 30);
		studentArr[1] = new Student(2, 3, 11, "김철수"); //studentArr[1].updateScore(10, 10, 10);
		studentArr[2] = new Student(1, 7, 3, "최미영"); //studentArr[2].updateScore(90, 80, 70);
		studentArr[3] = new Student(2, 1, 13, "박모모"); //studentArr[3].updateScore(50, 60, 75);
		
		// 학생 랜덤 점수 추가
		Random random = new Random();
		for(Student s : studentArr) {
			if( s==null ) break;
			s.setKor(random.nextInt(101));
			s.setEng(random.nextInt(101));
			s.setMath(random.nextInt(101));
		}
	}

	/**
	 * 1. 학생 추가 서비스
	 * 
	 * @param grade     : int
	 * @param classRoom : int
	 * @param number    : int
	 * @param name      : String
	 * 
	 * @return result : boolean (학생 추가 성공 시 true)
	 */
	public boolean addStudent(int grade, int classRoom, int number, String name) {

		// studentArr에서 비어있는 인덱스를 찾아 해당 인덱스에 매개변수를 이용해서 생성된 Student 객체를 대입 -> true 반환
		for (int i = 0; i < studentArr.length; i++) {

			// 배열 요소가 참조하는 객체가 없다면
			if (studentArr[i] == null) {
				// 비어있는 배열 요소에 매개변수를 이용해서 새 학생 객체를 만들어 참조값 대입
				studentArr[i] = new Student(grade, classRoom, number, name);
				return true;
			}
		}

		// 만약 비어있는 인덱스가 없을 경우 -> false 반환
		return false;
	}

	/**
	 * 2. 학생 전체 조회 서비스
	 * @return studentArr : Student[]
	 */
	public Student[] selectAll() {
		// studentArr를 반환
		return studentArr;
	}

	/**
	 * 3. 학생 정보 조회(인덱스) 서비스
	 * @param index : int
	 * @return studentArr[index] : Student 참조 변수
	 */
	public Student selectIndex(int index) {
		
		// 인덱스 값이 0~4가 아니면 배열 범위를 초과하여 ArrayIndexOutOfBoundsException 발생
		// => 해결방법 : 배열 범위가 넘어선 경우에 대한 별도 처리(null 반환)
		if( index < 0 || index > studentArr.length-1 ) {
			return null;
		}
		return studentArr[index];
	}

	/**
	 * 4. 학생 정보 조회(이름) 서비스
	 * @param name : String
	 * @return result : Student[], 조회된 학생이 없을 경우 null
	 */
	public Student[] selectName(String name) {
		
		// 이름이 일치하는 학생 모두를 저장할 객체배열 선언 및 초기화
		Student[] result = new Student[studentArr.length];
		int index = 0; // result에 값을 대입할 인덱스를 나타내는 변수
		
		// studentArr에서 이름이 일치하는 학생 찾기
		for(int i=0; i<studentArr.length; i++) {
			
			// studentArr[i]가 null인지 검사
			if( studentArr[i] == null ) break;

			// i번째 요소의 name과 매개변수 name이 같은 경우
			if( studentArr[i] != null && studentArr[i].getName().equals(name) ) {
				// result에 studentArr[i]의 값(주소)를 대입 (얕은 복사)
				result[index++] = studentArr[i]; // result[index]에 studentArr[i] 값 대입 후 index 값 증가 (후위 연산)
			}
		}
		
		// 이름이 일치하는 학생이 없어서 index가 증가하지 않았다면 null 반환
		if ( index == 0 )
			return null;
		
		return result;
	}
	
	/**
	 * 5. 학생 정보 수정(인덱스) 서비스
	 * @param index : int
	 * @param kor : int
	 * @param eng : int
	 * @param math : int
	 * @return 수정 성공시 true / 실패시 false
	 */
	public boolean updateStudent(int index, int kor, int eng, int math) {
		
		// 예상 가능한 문제
		// 1. index 범위를 초과한 입력		-> ArrayIndexOutOfBoundsException
		// 2. index 범위 요소가 null인 경우 -> NullPointerException
		if( index < 0 || index > studentArr.length-1 || studentArr[index] == null )
			return false;
		
//		studentArr[index].setKor(kor);
//		studentArr[index].setEng(eng);
//		studentArr[index].setMath(math);
		studentArr[index].updateScore(kor, eng, math);
		
		return true;
	}

	/**
	 * 6. 학생 총점 점수 합계, 평균, 최고점, 최저점 서비스
	 * @return arr : int[] (인덱스 순서대로 합계, 평균, 최고점, 최저점)
	 */
	public int[] sumAvgMaxMin() {
		
		int[] arr = new int[4];
		int totalSum = 0, max = 0, min = 300, count = 0;
		
		for( Student s : studentArr ) {
			if( s == null ) break;
			
			int sum = s.getKor() + s.getEng() + s.getMath();
			
			if( max < sum ) max = sum;
			if( min > sum ) min = sum;
			
			totalSum += sum;
			count++;
		}
		
		arr[0] = totalSum;			// 전체 학생의 점수 합
		arr[1] = totalSum / count;  // 전체 총점 평균
		arr[2] = max;				// 최고점
		arr[3] = min;				// 최저점
		
		return arr;
	}
}

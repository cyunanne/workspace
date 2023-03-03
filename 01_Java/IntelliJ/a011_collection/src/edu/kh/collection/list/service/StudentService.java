package edu.kh.collection.list.service;

import edu.kh.collection.list.dto.Student;

import java.util.*;

public class StudentService /*implements Comparator<Student>*/ {

    private List<Student> studentList = new ArrayList<>();
//    private List<Student> studentList = new LinkedList<>(); // List 구현 객체들마다 장단점이 있으므로 골라서 사용

    public StudentService() {
        studentList.add(new Student("홍길동", 3, 5, 17, "서울시 중구", 'M', 75));
        studentList.add(new Student("최유나", 1, 15, 49, "경기도 하남시", 'F', 80));
        studentList.add(new Student("김갑순", 2, 9, 33, "부산시 서면", 'F', 90));
        studentList.add(new Student("이미영", 2, 3, 8, "경기도 부천시", 'F', 60));
        studentList.add(new Student("박흥민", 1, 2, 29, "서울시 강남구", 'M', 45));
    }

    /**
     * studentList에 학생 추가
     * @param student
     * @return true
     */
    public boolean addStudent(Student student) {
        return studentList.add(student);
    }

    /**
     * 학생 전체 조회 서비스
     * @return studentList
     */
    public List<Student> selectAll() {
        return studentList;
    }

    /**
     * 학생 정보 수정 서비스
     * @param index
     * @param std
     * @return s:Student (수정되기 전 학생 정보)
     */
    public Student updateStudent(int index, Student std) {
        // E set(int index, E e)
        //   1) index에 위치하는 요소를 e로 변경
        //   2) 기존에 있던 요소 e2를 반환
        return studentList.set(index, std);
    }

    /**
     * 학생 정보 제거 서비스
     * @param index
     * @return s:Student (제거된 학생 정보)
     */
    public Student removeStudent(int index) {
        // E remove(int index): index번째 요소를 List에서 제거하여 반환
        // boolean remove(E e): List에서 e와 일치하는 요소를 찾아 있으면 제거하고 true / 없으면 false 반환
        return studentList.remove(index);
    }

    /**
     * 학생 이름 검색 서비스
     * @param name
     * @return list:List<Student> 이름이 일치하는 학생 리스트
     */
    public List<Student> selectName(String name) {

        // 1. 검색 결과를 저장할 리스트
        List<Student> list = new ArrayList<>();

        // 2. studentList의 모든 요소를 순차접근하면서 이름이 일치하는 학생을 list에 추가
        for(Student s : studentList) {
            if( s.getName().equals(name) )
                list.add(s);
        }

        // 3. 검색 결과 반환
        return list;
    }

    /**
     * 학생 주소 검색 서비스
     * @param addr
     * @return list:List<Student> 검색어가 주소에 포함된 학생 리스트
     */
    public List<Student> selectAddress(String addr) {

        List<Student> list = new ArrayList<>();

        for(Student s : studentList) {
            // String.contains(String str) 문자열에 str이 포함되어 있으면 true 반환
            if( s.getAddress().contains(addr) ) {
                list.add(s);
            }
        }

        return list;
    }

    /**
     * 학년별 조회 서비스
     * @param grade
     * @return list:List<Student> 조회된 학년 학생 리스트
     */
    public List<Student> selectGrade(int grade) {

        // 제네릭 타입추론: 생성되는 객체의 제네릭을 별도 작성하지 않아도 참조변수의 제네릭을 통해 타입을 유추
        // List<Student> list = new ArrayList<Student>();
        List<Student> list = new ArrayList<>();

        for(Student s : studentList) {
            if( s.getGrade() == grade ) {
                list.add(s);
            }
        }

        return list;
    }

    /**
     * 성별 학생 조회 서비스
     * @param gender
     * @return list:List<Student> 성별 학생 리스트
     */
    public List<Student> selectGender(char gender) {

        List<Student> list = new ArrayList<>();

        for(Student s : studentList) {
            if( s.getGender() == gender ) {
                list.add(s);
            }
        }

        return list;
    }

    /**
     * 성적 순서 조회 서비스
     * @return list:List<Student> 성적순으로 정렬된 학생 리스트
     *
     * Collections 클래스: 컬렉션에 도움되는 유용한 기능을 모은 클래스
     * Comparable<T> 인터페이스: 객체의 기본 정렬 기준을 제공하는 인터페이스
     *
     * <?> void Collections.sort(List<?> list)
     * <?>: 어떤 게 작성될지 모름 == 아무거나 작성 가능
     */
    public List<Student> selectScoreOrder() {
//        Collections.sort(studentList, Collections.reverseOrder()); // Student.compareTo() 사용
//        Collections.sort(studentList, this);                       // this.compare() 사용

        // studentList에 저장된 객체 Student의 오버라이딩 된 compareTo() 메서드를 이용해 정렬
        // => 현재 큰 숫자가 오른쪽으로 이동하도록 오버라이딩 됨(오름차순)
        // Collections.reverse(List<?> list): 리스트 순서를 반대로 뒤집음
        Collections.sort(studentList);
        Collections.reverse(studentList);
        return studentList;
    }

    /*@Override
    public int compare(Student o1, Student o2) {
        System.out.println("compare()");
        if( o1.getScore() > o2.getScore() ) return 1;
        if( o1.getScore() < o2.getScore() ) return -1;
        return 0;
    }*/
}

package edu.kh.collection.list.service;

import edu.kh.collection.list.dto.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> studentList = new ArrayList<>();

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
}

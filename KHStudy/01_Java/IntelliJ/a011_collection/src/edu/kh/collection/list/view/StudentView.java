package edu.kh.collection.list.view;

import edu.kh.collection.list.dto.Student;
import edu.kh.collection.list.service.StudentService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentView {

    private Scanner scanner = new Scanner(System.in);
    private StudentService service = new StudentService();

    public void displayMenu() {
        int input = 0;

        do {
            try {
                System.out.println("\n--- 학생 관리 프로그램 ---\n");
                System.out.println("1. 학생 정보 추가");
                System.out.println("2. 학생 전체 조회");
                System.out.println("3. 학생 정보 수정");
                System.out.println("4. 학정 정보 제거");
                System.out.println("5. 학생 이름 검색");
                System.out.println("6. 학생 주소 검색");
                System.out.println("7. 학년별 조회");
                System.out.println("8. 성별 조회");
                System.out.println("9. 성적 순서 조회");
                System.out.println("0. 프로그램 종료");

                System.out.println("\n--------------------");
                System.out.print("메뉴 선택 >> ");
                input = scanner.nextInt();
                scanner.nextLine(); // 버퍼에 남은 개행문자 제거
                System.out.println();

                switch (input) {
                    case 1: addStudent(); break;
                    case 2: selectAll(); break;
                    case 3: updateStudent(); break;
                    case 4: removeStudent(); break;
                    case 5: selectName(); break;
                    case 6: selectAddress(); break;
                    case 7: selectGrade(); break;
                    case 8: selectGender(); break;
                    case 9: selectScoreOrder(); break;
                    case 0: System.out.println("[ 프로그램 종료 ]"); break;
                    default:System.out.println("[ 잘못 입력하셨습니다. ]");
                }
            } catch(InputMismatchException e) {
                System.out.println("[ 잘못된 형식의 입력입니다. ]");
                scanner.nextLine(); // 입력 버퍼에 잘못 입려된 내용 제거
                input = -1; // 반복문이 종료되는 것을 방지
            }
            System.out.println();
        } while(input != 0);
    }

    /**
     * 학생 전체 정보를 입력 받아 Student 객체로 반환하는 메서드
     * @return std:Student
     */
    private Student inputStudent() {
        System.out.print("이름: ");
        String name = scanner.nextLine();
        System.out.print("학년: ");
        int grade = scanner.nextInt();
        System.out.print("반: ");
        int classRoom = scanner.nextInt();
        System.out.print("번호: ");
        int number = scanner.nextInt(); scanner.nextLine();
        System.out.print("주소: ");
        String address = scanner.nextLine();
        System.out.print("성별(M/F): ");
        char gender = scanner.nextLine().toUpperCase().charAt(0);
        // String.toUpperCase(): 모두 대문자로 변경
        // String.toLowerCase(): 모두 소문자로 변경
        System.out.print("성적: ");
        int score = scanner.nextInt();

        return new Student(name, grade, classRoom, number, address, gender, score);
    }

    /**
     * 학생 추가
     */
    private void addStudent() {
        System.out.println("\n--- 학생 정보 추가 ---\n");
        Student std = inputStudent();
        if(service.addStudent(std)) {
            System.out.println("[ 학생 정보가 추가되었습니다. ]");
        }
    }

    /**
     * 전체 학생 조회
     */
    private void selectAll() {
        System.out.println("\n--- 전체 학생 조회 ---\n");

        List<Student> list = service.selectAll();

        // 향상된 for문
        for(Student s : list) {
            // print 관련 메서드에서 참조변수를 출력하면 자동으로 .toString()을 붙여서 호출한다. (by 컴파일러)
            System.out.println(s);
        }
    }

    /**
     * 학생 정보 수정
     */
    private void updateStudent() {
        System.out.println("\n--- 학생 정보 수정 ---\n");

        System.out.println("<수정할 학생 정보 입력>");
        Student std = inputStudent();
        System.out.println("------------------");
        System.out.print("수정할 학생의 index: ");
        int index = scanner.nextInt();

        // s == 수정되기 전 학생 정보
        Student s = service.updateStudent(index, std);
        System.out.println(s.getName() + "의 정보가 수정 되었습니다.");
    }

    private void removeStudent() {
        System.out.println("\n--- 학생 정보 제거 ---\n");
        System.out.print("삭제할 학생의 인덱스 입력: ");
        int index = scanner.nextInt();

        Student s = service.removeStudent(index);
        System.out.println(s.getName() + "의 정보가 제거 되었습니다.");
    }

    private void selectName() {
        System.out.println("\n--- 학생 이름 검색 ---\n");
        System.out.print("검색할 이름: ");
        String name = scanner.nextLine();

        List<Student> list = service.selectName(name);

        if( list.isEmpty() ) { // 검색 결과가 없을 경우
            System.out.println("[ 검색 결과가 없습니다. ]");
        } else {
            for(Student s : list) System.out.println(s);
        }
    }

    private void selectAddress() {
        System.out.println("\n--- 학생 주소 검색 ---\n");
        System.out.print("주소에 포함된 단어 입력: ");
        String addr = scanner.nextLine();

        List<Student> list = service.selectAddress(addr);

        if( list.isEmpty() ) { // 검색 결과가 없을 경우
            System.out.println("[ 검색 결과가 없습니다. ]");
        } else {
            for(Student s : list)
                System.out.printf("%d학년 %d반 %d번 %s / 주소: %s\n",
                        s.getGrade(), s.getClassRoom(), s.getNumber(), s.getName(), s.getAddress());
        }
    }

    private void selectGrade() {
        System.out.println("\n--- 학년별 조회 ---\n");
        System.out.print("조회할 학년을 입력하세요: ");
        int grade = scanner.nextInt();

        List<Student> list = service.selectGrade(grade);

        if( list.isEmpty() ) { // 검색 결과가 없을 경우
            System.out.println("[ " + grade + "학년 학생이 존재하지 않습니다. ]");
        } else {
            System.out.println("[ " + grade + "학년 조회 결과 ]");
            for(Student s : list)
                System.out.printf("%d학년 %d반 %d번 %s\n",
                        s.getGrade(), s.getClassRoom(), s.getNumber(), s.getName());
        }
    }

    private void selectGender() {
        System.out.println("\n--- 성별 조회 ---\n");

        char gender;

        while(true) {
            System.out.print("조회할 성별을 입력하세요(M/F): ");
            gender = scanner.nextLine().toUpperCase().charAt(0);

            if (gender == 'M' || gender == 'F') break;

            System.out.println("M 또는 F만 입력 해주세요.");
        }

        List<Student> list = service.selectGender(gender);

        if( list.isEmpty() ) { // 검색 결과가 없을 경우
            System.out.println("[ 검색 결과가 없습니다. ]");
        } else {
            System.out.println("[ " + (gender=='M' ? "남" : "여") + "학생 목록 ]");
            for(Student s : list)
                System.out.printf("%d학년 %d반 %d번 %s(%c)\n",
                        s.getGrade(), s.getClassRoom(), s.getNumber(), s.getName(), s.getGender());
        }
    }

    private void selectScoreOrder() {
        System.out.println("\n--- 성적 순서 조회 ---\n");

        List<Student> list = service.selectScoreOrder(); // 성적 순서로 정렬

        for(Student s : list)
            System.out.println(s);
    }
}

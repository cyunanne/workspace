package edu.kh.io.service;

import edu.kh.io.dto.Student;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOService {

    // 스트림(Stream) : 데이터가 이동하는 통로. (기본적으로 한 쪽 방향으로만 흐름)
    // 바이트 기반 스트림 : 1byte 단위로 데이터를 입/출력 하는 스트림
    //                  -1byte 문자로 이루어진 text, 이미지, 영상, 파일 등 문자가 아닌 데이터 파일
    // 문자 기반 스트림   : 문자 단위로 데이터를 입/출력 하는 스트림
    //                  -문자로 이루어진 text, 채팅, 코드

    private String content = "Hello World~~~!!^^\n" + "1234567890\n" + "내일은 토요일 입니다.\n" + "$@#%^$&$%@#$";

    /**
     * 바이트 기반 출력
     */
    public void byteOutPut() {

        FileOutputStream fos = null;
        // 스트림 참조 변수 선언을 try 전에 한 이유 : finally에서도 해당 참조 변수를 사용할 수 있도록 try구문 밖에서 먼저 정의

        try {
//            fos = new FileOutputStream("byte/byteTest.txt"); // 덮어쓰기(기본값)
            fos = new FileOutputStream("byte/byteTest.txt", true); // append
            // 상대경로 기준? 프로젝트 폴더 내부

            // 경로 지정 방식
            // c:\tools\eclipse\ [절대 경로 방식] : 절대적인 기주점 하나를 기준으로 경로를 작성
            // byte/byteTest.txt [상대 경로 방식] : 현재위치를 기준으로 경로를 작성

            // 지금 작성하는 프로그램에서 지정된 경로로 파일을 내보냄(출력)
            // 만약 경로 제일 마지막에 작성한 파일이 존재하지 않는다면, 출력 구문 수행 시 자동으로 생성
            // FileOutputStream 발생 가능성이 있음

            // content에 작성된 문자를 1byte단위로 하나씩 쪼개기
            for (int i = 0; i < content.length(); i++) {
                char ch = content.charAt(i);
                fos.write(ch); // Unhandled exception type IOException -> catch문에 IOException 추가
                // 프로그램 -> 텍스트파일 : 프로그램이 텍스트파일에 쓴다(출력)
            }

            System.out.println("출력 완료");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { // 예외 발생 여부와 관계없이 무조건 수행
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 문자 기반 출력
     */
    public void charOutput() {
        FileWriter fw = null;

        try {
//            fw = new FileWriter("char/charTest.txt"); // 덮어쓰기(기본값)
            fw = new FileWriter("char/charTest.txt", true); // append
            fw.write(content);
            System.out.println("문자 기반 출력 완료");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) fw.close(); // 스트림에 남은 내용을 모두 밀어내고 닫기
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 바이트 기반 입력 스트림
     */
    public void byteInput() {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("char/charTest.txt");

            int value = 0; // 파일에서 읽어온 바이트 하나를 저장할 변수
            while (true) { // 파일 내용을 모두 읽을 때까지 반복
                value = fis.read(); // 다음 1byte를 읽어와 int형으로 반환 / 더 이상 읽어올 데이터가 없으면 -1 반환
                if (value == -1) break; // 파일을 다 읽어온 경우 반복 종료

                System.out.print((char) value); // int -> char로 형변환해서 문자 형태로 출력
            }
        } catch (FileNotFoundException e) { // 읽고자 하는 파일을 찾을 수 없음
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close(); // 스트림 닫기(메모리 반환)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 문자 기반 입력 스트림
     */
    public void charInput() {
        FileReader fr = null;

        try {
            fr = new FileReader("char/charTest.txt");

            int value = 0; // 파일에서 읽어온 문자 하나를 저장할 변수
            while (true) { // 파일 내용을 모두 읽을 때까지 반복

                value = fr.read(); // 다음 문자를 읽어와 int형으로 반환 / 더 이상 읽어올 데이터가 없으면 -1 반환
                if (value == -1) break; // 파일을 다 읽어온 경우 반복 종료

                System.out.print((char) value); // int -> char로 형변환해서 문자 형태로 출력
            }
        } catch (FileNotFoundException e) { // 읽고자 하는 파일을 찾을 수 없음
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) fr.close(); // 스트림 닫기(메모리 반환)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 어떤 형식의 파일이든 복사하기
     * - 바이트 기반 스트림 사용
     */
    public void fileCopy() {

        Scanner sc = new Scanner(System.in);

        // 바이트 기반 스트림 사용 + 성능 향상을 위한 보조 스트림(BufferedInput/OutputStream) 함께 사용
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            System.out.print("복사할 파일의 경로: ");
            String target = sc.nextLine();
            System.out.print("복사된 파일의 경로 + 파일명: ");
            String copy = sc.nextLine();

            bis = new BufferedInputStream(new FileInputStream(target));
            bos = new BufferedOutputStream(new FileOutputStream(copy));

            int value = 0;
            while (true) {
                value = bis.read();
                if(value == -1) break;
                bos.write(value);
            }
            System.out.println("복사 완료");
        } catch (FileNotFoundException e) {
            System.out.println("지정된 경로가 존재하지 않거나 파일이 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("입/출력 과정에서 문제가 발생했습니다.");
            e.printStackTrace();
        } finally {
            try {
                if(bis != null) bis.close();
                if(bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 객체 출력 보조 스트림
     * @ObjectXXXStream 객체를 파일 또는 네트워크를 통해 입/출력 할 수 있는 보조 스트림
     * @ObjectOutputStream 객체를 바이트 기반 스트림으로 출력할 수 있게 하는 보조 스트림
     * @' - 조건: 출력하려는 객체에 직렬화 가능을 나타내는 Serializable 인터페이스를 상속해야 함
     */
    public void objectOutput() {

        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("object/Student.txt"));
            Student s = new Student("최유나", 3, 15, 43, '여');// 내보낼 학생 객체 생성
            oos.writeObject(s); // 학생 객체를 파일로 출력
            System.out.println("학생 출력 완료");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if( oos != null ) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 객체 입력 보조 스트림
     */
    public void objectInput(){
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream("object/Student.txt"));
            Student s = (Student)ois.readObject(); // 직렬화된 객체 데이터를 읽어와 역직렬화 시켜 정상적인 객체 형태로 반환
            System.out.println(s);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null) ois.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 리스트에 Student 객체를 담아서 파일로 출력
     */
    public void listOutput() {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("object/studentList.ini"));
            List<Student> list = new ArrayList<>();
            list.add(new Student("최유나", 3, 15, 43, '여'));// 내보낼 학생 객체 생성
            list.add(new Student("A", 1, 3, 3, '남'));// 내보낼 학생 객체 생성
            list.add(new Student("B", 2, 5, 32, '여'));// 내보낼 학생 객체 생성
            list.add(new Student("C", 3, 4, 12, '남'));// 내보낼 학생 객체 생성
            // 컬렉션은 모두 직렬화 가능하도록 Serializable 인터페이스가 구현되어있음.
            // 단, 컬렉션에 저장하는 객체가 직렬화 가능하지 않다면 출력이 되지 않는다.
            // => NotSerializableException 발생
            oos.writeObject(list);
            System.out.println("학생 목록 출력 완료");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if( oos != null ) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void listInput(){
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream("object/studentList.ini"));
            List<Student> list = (List<Student>)ois.readObject(); // 직렬화된 객체 데이터를 읽어와 역직렬화 시켜 정상적인 객체 형태로 반환
            for(Student s : list)
                System.out.println(s);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null) ois.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
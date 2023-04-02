package edu.kh.jdbc.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
    public static void main(String[] args) {
        // XML(eXtensible Markup Language)
        // Properties Map<String, String>의 후손 클래스

        Scanner scanner = new Scanner(System.in);

        // Properties 객체 생성
        // key, value가 모두 String 고정이므로 Generic 선언 안 함
        Properties prop = new Properties();

        System.out.print("생성할 파일 이름 : ");
        String fileName = scanner.nextLine();

        // FileOutputStream 객체 생성 (바이트기반 스트림)
        try {
            FileOutputStream fos = new FileOutputStream(fileName + ".xml");

            // Properties 객체를 이용해서 xml 파일 생성
            // storToXML(바이트출력스트림, 설명) : XML 파일 생성
            prop.storeToXML(fos, fileName + ".xml file 입니다.");

            System.out.println(fileName + ".xml 파일 생성 완료");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

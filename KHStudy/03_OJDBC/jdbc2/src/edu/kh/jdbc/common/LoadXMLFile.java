package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoadXMLFile {
    public static void main(String[] args) {
        try {

            // Properties 객체 생성
            Properties prop = new Properties();

            // FileInputSteam 객체 생성
//            FileInputStream fis = new FileInputStream("test.xml");
            FileInputStream fis = new FileInputStream("driver.xml");

            // FileInputStream에 연결된 xml 파일을 읽어와 Properties 객체에 저장
            prop.loadFromXML(fis);

            System.out.println(prop);

            // String Properties.getProperty("key") : 전달된 key에 대응하는 value 반환
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String pw = prop.getProperty("pw");

            System.out.println("driver : " + driver);
            System.out.println("url : " + url);
            System.out.println("user : " + user);
            System.out.println("pw : " + pw);

            // DB 연결 테스트 : Connection 얻어오기
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pw);
            System.out.println(conn);
            conn.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
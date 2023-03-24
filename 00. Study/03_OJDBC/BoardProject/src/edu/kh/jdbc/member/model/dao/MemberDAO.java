package edu.kh.jdbc.member.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MemberDAO {

    // JDBC 객체 참조 변수
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // XML에 작성된 SQL을 읽어와 저장할 객체 참조 변수
    private Properties prop;

    public MemberDAO() {
        try {
            prop = new Properties();
            prop.loadFromXML(new FileInputStream("member-sql.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

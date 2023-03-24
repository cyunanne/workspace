package edu.kh.jdbc.main.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainDAO {

    private Statement stmt; // SQL 수행, 결과 반환
    private PreparedStatement pstmt; // placeholder 포함 SQL 작성, 수행, 결과 반환
    private ResultSet rs; // save the result of SELECT

    public MainDAO() {}
}

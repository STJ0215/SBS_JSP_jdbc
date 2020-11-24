package com.sbs.edu.jdbc;

import java.sql.*;


public class jdbc {
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static void main(String[] args) throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC&useUnicode=true&charcterEncoding=UTF8";
		String dbID = "root";
		String dbPW = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			
			System.out.println("conn Success!");
			//InsertData();
			//UpdateData();
			SelectData();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int InsertData() {
		int idx= -1;
		String SQL = " INSERT INTO board(name, content) VALUES(?, ?) ";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "John");
			pstmt.setString(2, "Hello");
			
			idx = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idx;
	}
	
	public static int UpdateData() {
		int idx= -1;
		String SQL = " UPDATE board SET name = ?, content = ? " + "WHERE id = 1 ";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "Paul");
			pstmt.setString(2, "GoodBye");
			
			idx = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idx;
	}
	
	public static void SelectData() {
		ResultSet rs = null;
		String SQL = " SELECT * FROM board ";
		SQL += " WHERE id = ? ";
		
		try {
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, 1);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				String cont = rs.getString("content");
				
				System.out.println(name + " : " + cont);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

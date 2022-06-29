package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUser {
	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/gb";
				String user = "root";
				String password = "1234";
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패 : " + e);
			} catch (SQLException e) {
				System.out.println("DB 연결 실패 : " + e);
			}
		}
		return conn;
	}

	public static void main(String[] args) {

		Connection conn = InsertUser.getConnection();

		String userid = "apple";
		String userpw = "abcd1234";
		String username = "김사과";
		
		String sql = "insert into user(userid,userpw,username) values(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, userpw);
			ps.setString(3, username);
			if (ps.executeUpdate() == 1) {
				System.out.println("데이터 추가 성공");
			} else {
				System.out.println("데이터 추가 실패");
			}
		} catch (SQLException e) {
			System.out.println("DB로 전송 실패 : " + e);
		}
	}
}
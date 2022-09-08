package net.koreate.db_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MysqlConnectorTest {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/spring_data";
	String user = "spring";
	String pass = "12345";
	
	@Test
	public void testConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println(conn + " : 연결 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 못 찾음");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류 : " + e.getMessage());
		}finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {}
		}
	}
}

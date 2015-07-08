package main.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost/url_shortener";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.jdbc.ConnectionFactory;
import main.models.User;

public class UserDAO {
	private Connection connection;

	public void setNewConnection() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public boolean verifyByEmail(String email) {
		PreparedStatement stmt;
		String sql = "select * from users where email = ?";

		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * retorna se a inserção é valida e ocorreu, retorna falso quando usuario ja
	 * existe
	 */
	public boolean insertUser(User user) throws SQLException {
		if (verifyByEmail(user.getEmail()))
			return false;

		PreparedStatement stmt;
		String sql = "insert into users (firstname,surname,email,password) values (?,?,?,?)";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getSurName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());

			stmt.execute();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public User getUser(String login, String password) throws SQLException {
		PreparedStatement stmt;

		String sql = "select * from users where email = ? and password = ?";
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, login);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			User user = new User();

			if (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setSurName(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
}

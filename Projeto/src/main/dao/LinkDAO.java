package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.models.Link;

public class LinkDAO {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public Link searchByCode(String code) {
		PreparedStatement stmt;
		String sql = "select * from links where code=?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Link link = new Link();
				link.setId(rs.getLong("id"));
				link.setCode(rs.getString("code"));
				link.setLongUrl(rs.getString("long_url"));

				return link;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Not found");
	}

	public Link searchByUrl(String url) {
		String code = getCodeFromUrl(url);
		return searchByCode(code);
	}

	public String getLongUrlFromCode(String code) {
		return "http://example.com/?" + code;
	}

	private static String getCodeFromUrl(String url) {
		String[] parts = url.split("/");
		return parts[parts.length - 1];
	}
}

package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.jdbc.ConnectionFactory;
import main.models.Link;
import main.models.User;
import main.utils.StringUtils;

public class LinkDAO {
	private Connection connection;
	
	public void setNewConnection() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	public List<Link> getLinksByUser(User user) {
		ArrayList<Link> links = new ArrayList<Link>();
		PreparedStatement stmt;
		String sql = "select * from links where user_id=?";
		try {
			stmt = connection.prepareStatement(sql,	Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, user.getId());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Link link = new Link();
				link.setId(rs.getLong("id"));
				link.setCode(rs.getString("code"));
				link.setLongUrl(rs.getString("long_url"));
				link.setUserId(rs.getLong("user_id"));
				link.setClicks(rs.getLong("clicks"));
				links.add(link);
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return links;
	}

	public String insertLink(Link link) {
		PreparedStatement stmt;
		String code = "";
		String sql = "insert into links (code,long_url,user_id) values (?,?,?)";
		try {
			stmt = connection.prepareStatement(sql,	Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "---");
			stmt.setString(2, link.getLongUrl());
			stmt.setLong(3, link.getUserId());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			int row = 0;
			if (rs.next())
				row = rs.getInt(1);
			code = createCodeAndUpdate(row);
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		link.setCode(code);
		return link.getShortUrl();
	}

	private String createCodeAndUpdate(int id) {
		PreparedStatement stmt;
		String sql = "update links set code = ? where id = ?";

		String code = StringUtils.getCodeFromId(id);

		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setLong(2, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return code;
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
				link.setUserId(rs.getLong("user_id"));
				link.setClicks(rs.getLong("clicks"));

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

	public void addClickToLink(Link link) {
		PreparedStatement stmt;
		String code = "";
		String sql = "update links set clicks = clicks+1 where id=?";
		try {
			stmt = connection.prepareStatement(sql,	Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, link.getId());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String getCodeFromUrl(String url) {
		String[] parts = url.split("/");
		String code = parts[parts.length - 1];
		
		if(code.endsWith("+")) {
			code = code.substring(0, code.length() - 1);
		}
		
		return code;
	}
}

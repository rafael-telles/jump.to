package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.org.hashids.Hashids;
import main.models.Link;
import main.utils.StringUtils;

public class LinkDAO {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	public String insertLink(Link link){
		PreparedStatement stmt;
		String shortUrl = "";
		String sql = "insert into links (code,long_url) values (?,?)";
		try{
			stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "---");
			stmt.setString(2, link.getLongUrl());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			int row = 0;
			if(rs.next())
				row = rs.getInt(1);		
			shortUrl = createCodeAndUpdate(row);
			stmt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return shortUrl;
	}
	
	private String createCodeAndUpdate(int id){
		PreparedStatement stmt;
		String sql = "update links set code = ? where id = ?";
		
		
		Hashids hashids = new Hashids();
		String code = hashids.encode(id);
		
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

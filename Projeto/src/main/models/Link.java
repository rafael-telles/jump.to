package main.models;

import main.Global;

public class Link {
	private long id = -1;
	private long userId = -1;
	private long clicks;

	public long getClicks() {
		return clicks;
	}

	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

	private String code;
	private String longUrl;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return String.format(Global.URL_PATTERN, code);
	}

	public String getStatisticsUrl() {
		return getShortUrl() + "+";
	}
}

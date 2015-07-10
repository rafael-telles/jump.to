package main;

public class Global {
	public static final String DOMAIN_NAME = "localhost";
	public static final String SITE_NAME = "jump.to";
	public static final String URL_PATTERN = "http://" + DOMAIN_NAME + "/u/%s";
	
	public String getDomainName() {
		return DOMAIN_NAME;
	}

	public String getSiteName() {
		return SITE_NAME;
	}

	public String getShortUrlPattern() {
		return URL_PATTERN;
	}
}

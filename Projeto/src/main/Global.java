package main;

public class Global {
	public static final String DOMAIN_NAME = "jump.to";
	public static final String SHORT_DOMAIN_NAME = "jm.pt";
	public static final String SITE_NAME = "jump.to";
	public static final String URL_PATTERN = "http://" + SHORT_DOMAIN_NAME + "/u/%s";
	
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

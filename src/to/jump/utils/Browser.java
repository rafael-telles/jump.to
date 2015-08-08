package to.jump.utils;

public enum Browser {
	GOOGLE_CHROME("Google Chrome", new String[] {"Chrome"}, new String[] {"Chromium"}),
	MOZILLA_FIREFOX("Mozilla Firefox", new String[] {"Firefox"}, new String[] {"Seamonkey"}),
	INTERNET_EXPLORER("Internet Explorer", new String[]{"MSIE", "Trident"}, new String[]{}),
	SAFARI("Safari", new String[]{"Safari"}, new String[]{"Chrome", "Chromium"}),
	OPERA("Opera", new String[]{"OPR", "Opera"}, new String[]{}),
	SEAMONKEY("Seamonkey", new String[]{"Seamonkey"}, new String[]{}),
	CHROMIUM("Chromium", new String[]{"Chromium"}, new String[]{}),
	UNKNOWN("?", new String[]{}, new String[]{});
	
	public final String name;
	public final String[] mustContain;
	public final String[] mustNotContain;
	
	Browser(String name, String[] mustContain, String[] mustNotContain) {
		this.name = name;
		this.mustContain = mustContain;
		this.mustNotContain = mustNotContain;
	}
	
	public boolean verify(String userAgent) {
		boolean contains = false;
		for (int i = 0; i < mustContain.length; i++) {
			contains |= userAgent.contains(mustContain[i]);
		}
		if(!contains) return false;
		
		for (int i = 0; i < mustNotContain.length; i++) {
			if(userAgent.contains(mustNotContain[i])) return false;
		}
		return true;
	}
	
	public static Browser identify(String userAgent) {
		Browser[] browsers = Browser.values();
		for (int i = 0; i < browsers.length; i++) {
			if(browsers[i].verify(userAgent)) {
				return browsers[i];
			}
		}
		
		return UNKNOWN;
	}
	
	public static String getBrowsersNames() {
		String ret = "[";
		Browser[] browsers = Browser.values();
		for (int i = 0; i < browsers.length; i++) {
			ret += "\"" + browsers[i].name + "\"";
			if(i < browsers.length - 1) ret += ", ";
		}
		ret += "]";
		return ret;
	}
}

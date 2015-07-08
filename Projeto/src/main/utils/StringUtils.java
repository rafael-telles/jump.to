package main.utils;

public class StringUtils {
	public static final char[] SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.-~".toCharArray();
	
	public static String getCodeFromId(long id) {
		int symbolsLen = SYMBOLS.length;
		String result = "";
		while(id > 0) {
			result = SYMBOLS[(int) (id % symbolsLen)] + result;
			id /= symbolsLen;
		}
		
		return result;
	}
}

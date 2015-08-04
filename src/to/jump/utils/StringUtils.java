package to.jump.utils;

import org.hashids.Hashids;

public class StringUtils {
	private static final Hashids HASHIDS = new Hashids();
	
	public static String getCodeFromId(long id) {
		return HASHIDS.encode(id);
	}
}

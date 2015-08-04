package to.jump.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Security {
	private static final String salt = "5YHSEFNSRYUSRTDGW4URTSHBSDFBSDGW";

	public static String encrypt(String msg) {
		try {
			byte[] bytes = (msg + salt).getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(bytes);
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);

			return hashtext;
		} catch (Exception e) {
		}
		return null;
	}
}
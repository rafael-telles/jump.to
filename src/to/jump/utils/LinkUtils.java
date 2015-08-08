package to.jump.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkUtils {	
		
	public static String getPageTitle(String url) {
		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();


			Pattern pattern = Pattern.compile("<title>(.*)<\\/title>");
			Matcher matcher = pattern.matcher(response.toString());

			if (matcher.find()) {
				return matcher.group(1);
			}
		} catch (IOException e) {
		}
		return url;
	}
}

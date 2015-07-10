package main.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Action {
	public void executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception;
}

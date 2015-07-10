package main.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.action.Action;
import main.models.Link;


@WebServlet("/urlshortener")
public class UrlShortenerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String actionStr = req.getParameter("action");
		String className = "main.action."+actionStr;
		
		
		try {
			
			Class<?> reqActionClass = Class.forName(className);
			
			Action action = (Action) reqActionClass.newInstance();
			
			String msg = action.executa(req, resp);
			
			Link link = new Link();
			link.setCode(msg);
			
			req.setAttribute("link", link);

			req.getRequestDispatcher("/generated.jsp").forward(req, resp);
			
		} catch (Exception e) {
			throw new ServletException("Erro na lógica de negócios -" +actionStr,e);
		}
	}

}

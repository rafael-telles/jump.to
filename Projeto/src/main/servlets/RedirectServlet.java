package main.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.LinkDAO;
import main.jdbc.ConnectionFactory;
import main.models.Link;

@WebServlet("/u/*")
public class RedirectServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		LinkDAO linkDAO = new LinkDAO();
		linkDAO.setConnection(new ConnectionFactory().getConnection());

		String nextUrl = "/redirect.jsp";
		String code = req.getRequestURI();
		
		Link link = linkDAO.searchByUrl(code);
		
		if(code.endsWith("+")) {
			code = code.substring(0, code.length() - 1);
			nextUrl = "/statistics.jsp";
		} else {		
			linkDAO.addClickToLink(link);
		}
		req.setAttribute("link", link);
		
		req.getRequestDispatcher(nextUrl).forward(req, resp);
	}
}

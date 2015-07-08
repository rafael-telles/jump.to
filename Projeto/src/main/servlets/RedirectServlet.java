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

		String url = req.getRequestURI();		
		Link link = linkDAO.searchByUrl(url);

		req.setAttribute("link", link);

		req.getRequestDispatcher("/redirect.jsp").forward(req, resp);
	}
}

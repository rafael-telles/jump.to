	package main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.LinkDAO;
import main.jdbc.ConnectionFactory;
import main.models.Link;


public class GenerateLink implements Action {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String longUrl = req.getParameter("longUrl");
		
		LinkDAO linkDAO = new LinkDAO();
		linkDAO.setConnection(new ConnectionFactory().getConnection());
		
		Link link = new Link();
		link.setLongUrl(longUrl);
		String shortUrl = linkDAO.insertLink(link);
		
		req.setAttribute("link", link);

		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}

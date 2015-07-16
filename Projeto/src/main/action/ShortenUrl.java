package main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.LinkDAO;
import main.jdbc.ConnectionFactory;
import main.models.Link;
import main.models.User;

public class ShortenUrl implements Action {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String longUrl = req.getParameter("longUrl");

		LinkDAO linkDAO = new LinkDAO();
		linkDAO.setConnection(new ConnectionFactory().getConnection());

		Link link = new Link();

		User user = (User) req.getSession().getAttribute("user");
		if (user != null) {
			link.setUserId(user.getId());
		}
		
		link.setLongUrl(longUrl);
		String shortUrl = linkDAO.insertLink(link);

		resp.sendRedirect(shortUrl + "+");
	}

}

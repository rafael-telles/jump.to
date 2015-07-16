package main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.LinkDAO;
import main.dao.UserDAO;
import main.jdbc.ConnectionFactory;
import main.models.User;
import main.utils.ErrorMessage;
import main.utils.Security;

public class Login implements Action {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String login = req.getParameter("email");
		String password = Security.encrypt(req.getParameter("password"));
		
		UserDAO userDAO = new UserDAO();
		userDAO.setConnection(new ConnectionFactory().getConnection());
		
		User user = userDAO.getUser(login, password);
		
		if(user != null){
			
			//System.out.println(new LinkDAO().getLinksByUser(user).size());
			
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("/dashboard");
		} else {
			req.getSession().setAttribute("error", new ErrorMessage("Não foi possível logar"));
			resp.sendRedirect("/login");
		}
	}

}

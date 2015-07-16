package main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.UserDAO;
import main.jdbc.ConnectionFactory;
import main.models.User;
import main.utils.ErrorMessage;
import main.utils.Security;

public class RegisterUser implements Action {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String firstName = req.getParameter("firstname");
		String surName = req.getParameter("surname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		User user = new User();

		user.setFirstName(firstName);
		user.setSurName(surName);
		user.setEmail(email);
		user.setPassword(Security.encrypt(password));

		UserDAO userDao = new UserDAO();
		userDao.setConnection(new ConnectionFactory().getConnection());

		boolean inserted = userDao.insertUser(user);

		if (inserted) {
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("/dashboard");
		} else {
			req.getSession().setAttribute("error", new ErrorMessage("Não foi possível registrar"));
			resp.sendRedirect("/register");
		}

	}

}

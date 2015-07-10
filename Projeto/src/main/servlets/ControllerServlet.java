package main.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.action.Action;
import main.models.Link;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String actionStr = req.getParameter("action");
		String className = "main.action." + actionStr;

		try {
			Class<?> actionClass = Class.forName(className);
			Action action = (Action) actionClass.newInstance();

			action.executa(req, resp);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

}

package to.jump.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import to.jump.dao.UserDAO;
import to.jump.models.User;
import to.jump.utils.Security;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "redirect:/dashboard";
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(HttpSession session, User user, BindingResult result, Model model)
			throws SQLException {
		user.encryptPassword();
		
		
		user = userDao.getUser(user.getEmail(), user.getPassword());
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/dashboard";
			
		} else {
			result.reject("user.not_found");
			return "login";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "redirect:/dashboard";
		}
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(HttpSession session, @Valid User user,
			BindingResult result) throws SQLException {
		if (result.hasErrors()) {
			return "register";
		}
		if (!userDao.verifyByEmail(user.getEmail())) {
			user.encryptPassword();
			userDao.insertUser(user);
			session.setAttribute("user", user);

			return "redirect:/dashboard";
		}
		result.reject("user.alredy_exists");
		return "register";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		
		return "redirect:/";
	}

}

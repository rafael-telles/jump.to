package to.jump.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import to.jump.dao.LinkDAO;

@Controller
public class MainController {
	
	@Autowired
	LinkDAO linkDao;
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "redirect:/dashboard";
		}
		return "index";
	}

	@RequestMapping("/dashboard")
	public String dashboard(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		return "dashboard";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(String q,
			HttpServletResponse httpServletResponse, Model model) {
		model.addAttribute("results", linkDao.searchLinks(q));

		return "search-results";
	}
}

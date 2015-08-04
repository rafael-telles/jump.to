package to.jump.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

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
}

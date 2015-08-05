package to.jump.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import to.jump.dao.ClickDAO;
import to.jump.dao.LinkDAO;
import to.jump.models.Click;
import to.jump.models.Link;
import to.jump.models.User;

@Controller
public class LinkController {

	@Autowired
	LinkDAO linkDao;
	@Autowired
	ClickDAO clickDao;

	@RequestMapping(value = "/shorten", method = RequestMethod.POST)
	public String shorten(Link link, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			link.setUserId(user.getId());
		}

		linkDao.insertLink(link);
		return "redirect:/u/" + link.getCode() + "+";
	}

	@RequestMapping(value = "/u/{code}", method = RequestMethod.GET)
	public RedirectView redirect(@PathVariable String code,
			final HttpServletResponse response,
			@RequestHeader(value="referer") String referrer,
			@RequestHeader(value="User-Agent") String userAgent) {
		Link link = linkDao.getLinkByCode(code);
		
		Click click = new Click();
		click.setLinkId(link.getId());
		click.setReferrer(referrer);
		click.setUserAgent(userAgent);
		
		clickDao.insertClick(click);

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(link.getLongUrl());
		return redirectView;
	}

	@RequestMapping(value = "/u/{code}+", method = RequestMethod.GET)
	public String statistics(@PathVariable String code,
			HttpServletResponse httpServletResponse, Model model) {
		Link link = linkDao.getLinkByCode(code);
		model.addAttribute("link", link);

		return "statistics";
	}

	@RequestMapping(value = "removeLink", method = RequestMethod.POST)
	public @ResponseBody String removeLink(long id,
			HttpServletResponse httpServletResponse, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			Link link = linkDao.getLinkById(id);
			if (link.getUserId() == user.getId()) {
				linkDao.remove(link);
				
				return "{\"error\": false, \"msg\": \"URL removida com sucesso!\"}";
			} else {
				return "{\"error\": true, \"msg\": \"Você não é o dono dessa URL!\"}";
			}
		}
		return "{\"error\": true, \"msg\": \"O que está tentando fazer?\"}";
	}
}

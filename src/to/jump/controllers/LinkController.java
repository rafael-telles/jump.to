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
import to.jump.utils.Browser;
import to.jump.utils.LinkUtils;

@Controller
public class LinkController {

	@Autowired
	LinkDAO linkDao;
	@Autowired
	ClickDAO clickDao;

	@RequestMapping(value = "/shorten", method = RequestMethod.POST)
	public String shorten(Link link, HttpSession session) {
		link.setTitle(LinkUtils.getPageTitle(link.getLongUrl()));

		User user = (User) session.getAttribute("user");
		if (user != null) {
			link.setUserId(user.getId());
		}
		linkDao.insertLink(link);

		if (user != null) {
			return "redirect:/e/" + link.getCode();
		} else {
			return "redirect:/s/" + link.getCode();
		}
	}

	@RequestMapping(value = "/r/{code}", method = RequestMethod.GET)
	public RedirectView redirect(@PathVariable String code,
			final HttpServletResponse response,
			@RequestHeader(value = "User-Agent") String userAgent) {
		Link link = linkDao.getLinkByCode(code);

		Click click = new Click();
		click.setLinkId(link.getId());
		click.setBrowser(Browser.identify(userAgent).name);

		clickDao.insertClick(click);

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(link.getLongUrl());
		return redirectView;
	}

	@RequestMapping(value = "/s/{code}", method = RequestMethod.GET)
	public String statistics(@PathVariable String code,
			HttpServletResponse httpServletResponse, Model model) {
		Link link = linkDao.getLinkByCode(code);
		model.addAttribute("link", link);

		return "statistics";
	}

	@RequestMapping(value = "removeLink", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String removeLink(long id,
			HttpServletResponse httpServletResponse, Model model,
			HttpSession session) {

		User user = (User) session.getAttribute("user");
		if (user != null) {
			Link link = linkDao.getLinkById(id);
			if (link.getUserId() == user.getId()) {
				linkDao.remove(link);

				return "{\"error\": false, \"msg\": \"URL removida com sucesso!\"}";
			}
		}
		return "{\"error\": true, \"msg\": \"Voc� n�o � o dono dessa URL!\"}";
	}

	@RequestMapping(value = "/e/{code}", method = RequestMethod.GET)
	public String edit(@PathVariable String code,
			HttpServletResponse httpServletResponse, Model model,
			HttpSession session) {
		Link link = linkDao.getLinkByCode(code);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (link.getUserId() == user.getId()) {
				model.addAttribute("link", link);
				return "edit";
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/updatelink", method = RequestMethod.POST)
	public String UpdateLink(HttpServletResponse httpServletResponse,
			Link link, Model model, HttpSession session) {
		Link linkToUpdate = linkDao.getLinkById(link.getId());
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (linkToUpdate.getUserId() == user.getId()) {
				linkToUpdate.setTags(link.getTags());
				linkToUpdate.setDescription(link.getDescription());
				linkDao.updateLink(linkToUpdate);
				return "redirect:/s/" + linkToUpdate.getCode();
			}
		}
		return "redirect:/";
	}
}

package to.jump.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import to.jump.models.Link;
import to.jump.models.User;
import to.jump.utils.Browser;
import to.jump.utils.DateUtils;
import to.jump.utils.StringUtils;

@Repository
@Transactional
public class LinkDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ClickDAO clickDao;

	public void remove(Link link) {
		sessionFactory.getCurrentSession().delete(link);
	}

	public Link getLinkById(long id) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("select a from Link a where id = :id")
				.setParameter("id", id).list();

		if (list.isEmpty()) {
			return null;
		} else {
			return (Link) list.get(0);
		}
	}

	public List<Link> getLinksByUser(User user) {
		if (user != null) {
			return sessionFactory.getCurrentSession()
					.createQuery("select a from Link a where userId = :userId order by createTime desc")
					.setParameter("userId", user.getId()).list();
		} else {
			return new ArrayList<Link>();
		}
	}

	public List<Link> searchLinks(String query) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"select a from Link a where "
						+ "title like '%'||:query||'%' "
						//+ "or description like '%'||:query||'%' "
						+ "or tags like '%'||:query||'%' "
						+ "and userId is not null "
						//+ "group by longUrl "
						+ "order by clicks")
				.setParameter("query", query).list();
	}

	public String insertLink(Link link) {
		link.setCreateTime(new Date());
		
		sessionFactory.getCurrentSession().persist(link);
		sessionFactory.getCurrentSession().flush();
		createCodeAndUpdate(link);

		return link.getShortUrl();
	}

	public void updateLink(Link link) {
		sessionFactory.getCurrentSession().update(link);
	}
	
	private String createCodeAndUpdate(Link link) {
		link.setCode(StringUtils.getCodeFromId(link.getId()));
		updateLink(link);
		return link.getCode();
	}

	public Link getLinkByCode(String code) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("select a from Link a where code = :code")
				.setParameter("code", code).list();
		if (list.isEmpty()) {
			return null;
		} else {
			return (Link) list.get(0);
		}
	}

	public Link getLinkByUrl(String url) {
		String code = getCodeFromUrl(url);
		return getLinkByCode(code);
	}
	
	public String getClicksChartData(Link link) {
		Date day = new Date();
		String ret = "]";
		for (int i = 0; i < 7; i++) {
			ret = clickDao.countClicksOnDay(link, day) + ret;
			if(i < 6) ret = ", " + ret;
			
			day = DateUtils.addDays(day, -1);
		}
		ret = "[" + ret;
		return ret;
	}
	
	public String getBrowsersChartData(Link link) {
		String ret = "[";
		Browser[] browsers = Browser.values();
		for (int i = 0; i < browsers.length; i++) {
			ret += clickDao.countClicksByBrowser(link, browsers[i].name);
			if(i < browsers.length - 1) ret += ", ";
		}
		ret += "]";
		return ret;
	}

	private static String getCodeFromUrl(String url) {
		String[] parts = url.split("/");
		String code = parts[parts.length - 1];

		if (code.endsWith("+")) {
			code = code.substring(0, code.length() - 1);
		}

		return code;
	}
}

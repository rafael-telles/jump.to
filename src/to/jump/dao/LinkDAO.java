package to.jump.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import to.jump.models.Link;
import to.jump.models.User;
import to.jump.utils.StringUtils;

@Repository
@Transactional
public class LinkDAO {
	@Autowired
	private SessionFactory sessionFactory;

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
					.createQuery("select a from Link a where userId = :userId")
					.setParameter("userId", user.getId()).list();
		} else {
			return new ArrayList<Link>();
		}
	}

	public List<Link> getLinksByTag(String tag) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"select a from Link a where tags like '%'||:tag||'%'")
				.setParameter("tag", tag).list();

	}

	public String insertLink(Link link) {
		sessionFactory.getCurrentSession().persist(link);
		sessionFactory.getCurrentSession().flush();
		createCodeAndUpdate(link);

		return link.getShortUrl();
	}

	private String createCodeAndUpdate(Link link) {
		link.setCode(StringUtils.getCodeFromId(link.getId()));
		sessionFactory.getCurrentSession().update(link);
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

	private static String getCodeFromUrl(String url) {
		String[] parts = url.split("/");
		String code = parts[parts.length - 1];

		if (code.endsWith("+")) {
			code = code.substring(0, code.length() - 1);
		}

		return code;
	}
}

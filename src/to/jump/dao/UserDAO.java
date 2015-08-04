package to.jump.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import to.jump.models.User;

@Repository
@Transactional
public class UserDAO {
	@Autowired
    private SessionFactory sessionFactory;

	public boolean verifyByEmail(String email) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("select a from User a where email = :email")
				.setParameter("email", email)
				.list();
		
		return !list.isEmpty();
	}
	
	public boolean insertUser(User user) throws SQLException {
		sessionFactory.getCurrentSession().persist(user);
		sessionFactory.getCurrentSession().flush();
		
		return true;
	}

	public User getUser(String email, String password) throws SQLException {
		List list = sessionFactory.getCurrentSession()
				.createQuery("select a from User a where email = :email and password = :password")
				.setParameter("email", email)
				.setParameter("password", password)
				.list();
		
		if(list.isEmpty()) {
			return null;
		} else {
			return (User) list.get(0);
		}
	}
}

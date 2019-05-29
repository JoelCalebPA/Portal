package com.domain.portal.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.domain.portal.model.Role;
import com.domain.portal.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUser(String username) {
		return this.sessionFactory.getCurrentSession().createNamedQuery("User.find", User.class)
				.setParameter("username", username).getSingleResult();
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public Role findRoleUser() {
		return this.sessionFactory.getCurrentSession().createNamedQuery("Role.find", Role.class)
				.setParameter("idRol", (long) 1).getSingleResult();
	}

	@Override
	public Role findRoleByUser(String username) {
		return this.sessionFactory.getCurrentSession().createNamedQuery("Role.findByUser", Role.class)
				.setParameter("username", username).getSingleResult();
	}

}

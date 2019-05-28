package com.domain.portal.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.domain.portal.model.Role;
import com.domain.portal.model.User;

public class UsuarioDao {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioDao.class);

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EntityManagerFactory emf;

	public UsuarioDao() {
	}

	@Transactional(readOnly = true)
	public User findUser(String username) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<User> user = em.createNamedQuery("User.find", User.class);
			return user.setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			logger.warn("Usuario " + username + " no encontrado.");
			return null;
		} finally {
			em.close();
		}
	}

	@Transactional(readOnly = true)
	public String findRoleByUser(String username) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<String> role = em.createNamedQuery("Role.findByUser", String.class);
			return role.setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			logger.warn("Rol para el usuario " + username + " no encontrado.");
			return null;
		} finally {
			em.close();
		}

	}

	@Transactional
	public void saveUser(User user) {
		EntityManager em = emf.createEntityManager();
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRol(findUserRole());
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			logger.info("Usuario registrado.");
		} catch (Exception e) {
			logger.warn("Usuario no registrado.");
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Transactional(readOnly = true)
	public Role findUserRole() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Role> role = em.createNamedQuery("Role.find", Role.class);
			return role.setParameter("idRol", (long) 1).getSingleResult();
		} catch (Exception e) {
			logger.warn("ROLE_USER no encontrado.");
			return null;
		} finally {
			em.close();
		}
	}

}

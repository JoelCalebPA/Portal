package com.domain.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.portal.dao.UserDAO;
import com.domain.portal.model.Role;
import com.domain.portal.model.User;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public User findUser(String username) {
		try {
			return userDAO.findUser(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Role findRoleUser() {
		try {
			return userDAO.findRoleUser();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Role findRoleByUser(String username) {
		try {
			return userDAO.findRoleByUser(username);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findOne(long id) {
		try {
			return userDAO.findOne(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		try {
			return userDAO.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void save(User entity) {
		try {
			userDAO.save(entity);
		} catch (Exception e) {
		}
	}

	@Override
	public User update(User entity) {
		try {
			return userDAO.update(entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delete(User entity) {
		try {
			userDAO.delete(entity);
		} catch (Exception e) {
		}
	}

	@Override
	public void deleteById(long id) {
		try {
			userDAO.deleteById(id);
		} catch (Exception e) {
		}
	}

}

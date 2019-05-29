package com.domain.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.portal.dao.UserDao;
import com.domain.portal.model.Role;
import com.domain.portal.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public User findUser(String username) {
		try {
			return userDao.findUser(username);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		try {
			userDao.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Role findRoleUser() {
		try {
			return userDao.findRoleUser();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Role findRoleByUser(String username) {
		try {
			return userDao.findRoleByUser(username);
		} catch (Exception e) {
			return null;
		}
	}

}

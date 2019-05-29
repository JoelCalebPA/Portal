package com.domain.portal.dao;

import com.domain.portal.model.Role;
import com.domain.portal.model.User;

public interface UserDao {

	User findUser(String username);

	void saveUser(User user);

	Role findRoleUser();

	Role findRoleByUser(String username);

}

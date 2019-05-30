package com.domain.portal.service;

import com.domain.portal.model.Role;
import com.domain.portal.model.User;

public interface UserService {

	User findUser(String username);

	void saveUser(User user);

	Role findRoleUser();

	Role findRoleByUser(String username);

}

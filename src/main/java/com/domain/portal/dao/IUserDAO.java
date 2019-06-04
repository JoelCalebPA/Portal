package com.domain.portal.dao;

import com.domain.portal.model.Role;
import com.domain.portal.model.User;

public interface IUserDAO {

	User findUser(String username);

	Role findRoleUser();

	Role findRoleByUser(String username);

}

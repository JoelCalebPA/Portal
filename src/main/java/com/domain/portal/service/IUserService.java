package com.domain.portal.service;

import com.domain.portal.model.Role;
import com.domain.portal.model.User;

public interface IUserService extends IHibernateDAO<User> {

	User findUser(String username);

	Role findRoleUser();

	Role findRoleByUser(String username);

}

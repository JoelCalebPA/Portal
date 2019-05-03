package com.domain.portal.service;

import com.domain.portal.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

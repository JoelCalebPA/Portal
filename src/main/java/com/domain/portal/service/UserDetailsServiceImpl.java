package com.domain.portal.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import com.domain.portal.dao.UsuarioDao;
import com.domain.portal.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioDao dao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		User user = dao.findUser(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		try {
			grantedAuthorities.add(new SimpleGrantedAuthority(dao.findRoleByUser(user.getUsuario())));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPassword(),
				grantedAuthorities);
	}
}

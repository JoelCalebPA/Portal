package com.domain.portal.auth;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.portal.dao.UsuarioDao;
import com.domain.portal.model.User;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private LdapAuthenticationProvider ldapProvider;

	@Autowired
	private UsuarioDao dao;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		try {
			Authentication auth = ldapProvider.authenticate(authentication);
			log.info("Login through ldap provider");
			grantedAuthorities = auth.getAuthorities().stream().collect(Collectors.toSet());
			log.info("Loaded authorities for user: " + auth.getName());
			if (dao.findUser(auth.getName()) == null) {
				User user = new User();
				user.setUsuario(auth.getName());
				user.setPassword(auth.getCredentials().toString());
				user.setRol(dao.findUserRole());
				dao.saveUser(user);
			}
			return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), grantedAuthorities);
		} catch (Exception e) {
			try {
				log.info("Login through jdbc provider");
				User user = dao.findUser(authentication.getName());
				grantedAuthorities.add(new SimpleGrantedAuthority(dao.findRoleByUser(user.getUsuario())));
				log.info("Loaded authorities for user: " + user.getUsuario());
				return new UsernamePasswordAuthenticationToken(user.getUsuario(), user.getPassword(),
						grantedAuthorities);
			} catch (Exception e2) {
				log.warn(e2.getMessage(), e2);
				throw new BadCredentialsException("Login failed...!");
			}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}

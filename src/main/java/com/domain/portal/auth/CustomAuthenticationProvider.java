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

import com.domain.portal.model.Role;
import com.domain.portal.model.User;
import com.domain.portal.repository.UserRepository;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	LdapAuthenticationProvider ldapProvider;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		try {
			Authentication auth = ldapProvider.authenticate(authentication);
			log.info("Login through ldap provider");
			grantedAuthorities = auth.getAuthorities().stream().collect(Collectors.toSet());
			log.info("Loaded authorities for user: " + auth.getName());
			if (userRepository.findByUsername(auth.getName()) == null) {
				User user = new User();
				user.setUsername(auth.getName());
				user.setPassword((String) auth.getCredentials());
				userRepository.save(user);
			}
			return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), grantedAuthorities);
		} catch (Exception e) {
			try {
				log.info("Login through jdbc provider");
				User user = userRepository.findByUsername(authentication.getName());
				for (Role role : user.getRoles()) {
					grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
				}
				log.info("Loaded authorities for user: " + user.getUsername());
				return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
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

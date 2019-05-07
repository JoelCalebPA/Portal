package com.domain.portal.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.domain.portal.auth.CustomUser;

public class PrincipalUtils {
	
	private static Logger log = LoggerFactory.getLogger(PrincipalUtils.class);
	private static final String ROLE_PREFIX = "ROLE_";

	public static CustomUser getUser() {
		Authentication auth = getAuthentication();
		return getUser(auth);
	}

	public static CustomUser getUser(Authentication auth) {
		CustomUser user = null;

		if (auth != null) {
			if (auth.getPrincipal() instanceof CustomUser) {
				user = (CustomUser) auth.getPrincipal();
			}
		}

		return user;
	}

	public static Set<String> getRoles() {
		Authentication auth = getAuthentication();
		Set<String> roles = new HashSet<>();

		if (auth != null) {
			for (GrantedAuthority ga : auth.getAuthorities()) {
				roles.add(ga.getAuthority());
			}
		}

		return roles;
	}

	public static boolean hasRole(String role) {
		Authentication auth = getAuthentication();

		if (auth != null) {
			UserDetails user = (UserDetails) auth.getPrincipal();
			String defaultedRole = getRoleWithDefaultPrefix(ROLE_PREFIX, role);

			for (GrantedAuthority ga : user.getAuthorities()) {
				if (ga.getAuthority().equals(defaultedRole)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean hasAnyRole(String... roles) {
		Authentication auth = getAuthentication();

		if (auth != null) {
			UserDetails user = (UserDetails) auth.getPrincipal();

			for (String role : roles) {
				String defaultedRole = getRoleWithDefaultPrefix(ROLE_PREFIX, role);

				for (GrantedAuthority ga : user.getAuthorities()) {
					if (ga.getAuthority().equals(defaultedRole)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static void setAuthentication(Authentication auth) {
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	public static Authentication createAuthentication(String user, Set<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return new UsernamePasswordAuthenticationToken(user, null, authorities);
	}

	private static String getRoleWithDefaultPrefix(String defaultRolePrefix, String role) {
		if (role == null) {
			return role;
		}

		if (defaultRolePrefix == null || defaultRolePrefix.length() == 0) {
			return role;
		}
		if (role.startsWith(defaultRolePrefix)) {
			return role;
		}

		return defaultRolePrefix + role;
	}
}

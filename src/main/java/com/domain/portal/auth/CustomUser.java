package com.domain.portal.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
	
	private static final long serialVersionUID = 1L;
	private String fullName;
	private String email;

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
	                  boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String fullName) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.setFullName(fullName);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		String s = super.toString();
		StringBuilder builder = new StringBuilder(s);
		builder.append("[fullName = " + this.fullName + "]");
		return builder.toString();
	}

}

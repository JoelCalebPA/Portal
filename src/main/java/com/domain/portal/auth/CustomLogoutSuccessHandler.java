package com.domain.portal.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

@Service("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	private static final Logger log = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null && authentication.getDetails() != null) {
			log.debug("session before invalidate : " + request.getSession().getCreationTime());
			request.getSession(false).invalidate();
			log.debug("invalidate session");
			log.debug("session after invalidate : " + request.getSession(false));
			log.info(authentication.getName() + " successfully logged out");
		}

		String url = request.getContextPath();
		response.sendRedirect(url);
	}
}

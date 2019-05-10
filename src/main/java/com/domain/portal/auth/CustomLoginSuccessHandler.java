/**
 * OpenKM, Open Document Management System (http://www.openkm.com)
 * Copyright (c) 2006-2016  Paco Avila & Josep Llort
 * <p>
 * No bytes were intentionally harmed during the development of this application.
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.domain.portal.auth;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.domain.portal.repository.UserRepository;
import com.domain.portal.service.OpenkmService;
import com.openkm.sdk4j.bean.Document;

@Service("customLoginSuccessHandler")
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private static final Logger log = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OpenkmService okmService;

	public CustomLoginSuccessHandler() {
	}

	/**
	 * This method will redirect to the page that was called.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setHeader("x-frame-options", "allow");
		log.debug("Session: {}", session);
		try {
			session.setAttribute("user", userRepository.findByUsername(authentication.getName()));
			List<Document> documents = okmService.getDocuments(authentication.getName());
			Collections.sort(documents, documentsComparator);
			session.setAttribute("documents", documents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("Data charged for user: {}" + authentication.getName());
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private Comparator<Document> documentsComparator = new Comparator<Document>() {
		@Override
		public int compare(Document o1, Document o2) {
			return o1.getCreated().compareTo(o2.getCreated());
		}
	};

}

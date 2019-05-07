package com.domain.portal.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.portal.config.Config;
import com.domain.portal.service.OpenkmService;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

@Controller
public class UserController {

//	@Autowired
//	@Qualifier(value = "entityManagerFactory")
//	EntityManagerFactory entityManagerFactory;
//	
//	@Autowired
//	@Qualifier(value = "transactionManager")
//	JpaTransactionManager transactionManager;

	@Autowired
	private OpenkmService okmService;

	@Autowired
	private Config configService;

	private Comparator<Document> documentsComparator = new Comparator<Document>() {

		@Override
		public int compare(Document o1, Document o2) {
			return o1.getCreated().compareTo(o2.getCreated());
		}
	};

	@GetMapping("/user")
	public ModelAndView getHome(Model model, HttpServletRequest request) {
		ModelAndView view;
		try {
			view = new ModelAndView("user/home");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Document> documents = okmService.getDocuments(auth.getName());
			Collections.sort(documents, documentsComparator);
			Document doc = documents.iterator().next();
			view.addObject("document", doc);
			view.addObject("previewUrl", generatePreviewUrl(doc, request));
		} catch (Exception e) {
			view = new ModelAndView("error");
			e.printStackTrace();
		}
		return view;
	}

	@GetMapping(path = "/user/record")
	public ModelAndView getRecord(Model model, HttpServletRequest request) {
		ModelAndView view;
		try {
			view = new ModelAndView("user/record");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			model.addAttribute("records", okmService.getDocuments(auth.getName()));
//			model.addAttribute("previewUrl", generatePreviewUrl(doc, request));
		} catch (Exception e) {
			e.printStackTrace();
			view = new ModelAndView("error");
		}
		return view;
	}

	@GetMapping("/user/account")
	public String getAccount() {
		return "user/account";
	}

	private String generatePreviewUrl(Document doc, HttpServletRequest request) throws RepositoryException,
			AccessDeniedException, PathNotFoundException, DatabaseException, UnknowException, WebserviceException {
		StringBuffer previewUrl = new StringBuffer();
		previewUrl.append(configService.getPreviewKcenterToOpenKMUrl());
		previewUrl.append("?");
		previewUrl.append("mimeType=").append(doc.getMimeType());
		previewUrl.append("&docUrl=");
		previewUrl.append(configService.OPENKM_PREVIEW_DOWNLOAD_URL);
		previewUrl.append("/downloadForPreview");
		previewUrl.append(";jsessionid=");
		previewUrl.append(request.getSession().getId());
		previewUrl.append("?node=");
		previewUrl.append(doc.getUuid());
		previewUrl.append("%26attachment=true");
		return previewUrl.toString();
	}

}

package com.domain.portal.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.domain.portal.config.Config;
import com.domain.portal.model.DocumentType;
import com.domain.portal.model.User;
import com.domain.portal.model.mapper.Mapper;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

@Controller
@SessionAttributes("documents")
public class UserController {

//	@Autowired
//	@Qualifier(value = "entityManagerFactory")
//	EntityManagerFactory entityManagerFactory;
//	
//	@Autowired
//	@Qualifier(value = "transactionManager")
//	JpaTransactionManager transactionManager;
//
//	@Autowired
//	private OpenkmService okmService;
//
//	@Autowired
//	private UserRepository userRepository;

	@Autowired
	private Config configService;

	@ModelAttribute("documents")
	public List<Document> documents() {
		return new ArrayList<Document>();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/user")
	public ModelAndView getHome(@ModelAttribute("documents") List<Document> documents, HttpServletRequest request) {
		ModelAndView view;
		HttpSession session = request.getSession();
		try {
			view = new ModelAndView("user/home");
			documents = (List<Document>) session.getAttribute("documents");
			session.setAttribute("documents", documents);
			Document doc = documents.iterator().next();
			DocumentType docT = Mapper.docToDocType(doc, generatePreviewUrl(doc, request, true));
			view.addObject("currentDoc", docT);
		} catch (Exception e) {
			view = new ModelAndView("error");
			e.printStackTrace();
		}
		return view;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/user/record")
	public ModelAndView getRecord(@ModelAttribute("documents") List<Document> documents, HttpServletRequest request) {
		ModelAndView view;
		HttpSession session = request.getSession();
		try {
			view = new ModelAndView("user/record");
			documents = (List<Document>) session.getAttribute("documents");
			List<DocumentType> docTypes = new ArrayList<DocumentType>();
			for (Document doc : documents) {
				docTypes.add(Mapper.docToDocType(doc, generatePreviewUrl(doc, request, false)));
			}
			view.addObject("documents", docTypes);
		} catch (Exception e) {
			e.printStackTrace();
			view = new ModelAndView("error");
		}
		return view;
	}

	@GetMapping("/user/account")
	public ModelAndView getAccount(HttpServletRequest request) {
		ModelAndView view;
		HttpSession session = request.getSession();
		try {
			view = new ModelAndView("user/account");
			User user = (User) session.getAttribute("user");
			view.addObject("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			view = new ModelAndView("error");
		}
		return view;
	}

	private String generatePreviewUrl(Document doc, HttpServletRequest request, boolean attachment) {
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
		if (!attachment)
			previewUrl.append("%26attachment=false");
		else
			previewUrl.append("%26attachment=true");
		return previewUrl.toString();
	}

}

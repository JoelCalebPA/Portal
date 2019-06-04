package com.domain.portal.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.domain.portal.config.Config;
import com.domain.portal.model.DocumentType;
import com.domain.portal.model.User;
import com.domain.portal.service.OpenkmService;
import com.domain.portal.service.IUserService;
import com.domain.portal.util.WebUtils;
import com.openkm.sdk4j.bean.Document;

@Controller
@SessionAttributes("documents")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private OpenkmService okmService;

	@Autowired
	private IUserService userService;

	@Autowired
	private Config configService;

	@ModelAttribute("documents")
	public List<DocumentType> documents() {
		return new ArrayList<DocumentType>();
	}

	@GetMapping("/user")
	public ModelAndView getHome(@ModelAttribute("documents") List<DocumentType> documents, HttpServletRequest request,
			Authentication auth) {
		ModelAndView view;
		try {
			view = new ModelAndView("user/home");
			documents = (List<DocumentType>) okmService.getDocuments(auth.getName());
			DocumentType doc = documents.iterator().next();
			doc.setDownloadUrl(generatePreviewUrl(doc, request));
			view.addObject("currentDoc", doc);
			view.addObject("active", "home");
		} catch (Exception e) {
			view = new ModelAndView("user/home");
			view.addObject("noDocs", true);
			e.printStackTrace();
		}
		return view;
	}

	@GetMapping(path = "/user/record")
	public ModelAndView getRecord(@ModelAttribute("documents") List<DocumentType> documents, HttpServletRequest request,
			Authentication auth) {
		ModelAndView view;
		try {
			view = new ModelAndView("user/record");
			documents = (List<DocumentType>) okmService.getDocuments(auth.getName());
			for (DocumentType doc : documents) {
				doc.setDownloadUrl(generatePreviewUrl(doc, request));
			}
			view.addObject("documents", documents);
			view.addObject("active", "record");
		} catch (Exception e) {
			e.printStackTrace();
			view = new ModelAndView("error");
		}
		return view;
	}

	@GetMapping("/user/account")
	public ModelAndView getAccount(HttpServletRequest request, Authentication auth) {
		ModelAndView view;
		try {
			view = new ModelAndView("user/account");
			User user = userService.findUser(auth.getName());
			view.addObject("user", user);
			view.addObject("active", "account");
		} catch (Exception e) {
			e.printStackTrace();
			view = new ModelAndView("error");
		}
		return view;
	}

	private String generatePreviewUrl(DocumentType doc, HttpServletRequest request) {
		StringBuffer previewUrl = new StringBuffer();
		previewUrl.append(configService.getPreviewKcenterToOpenKMUrl());
		previewUrl.append("?");
		previewUrl.append("mimeType=").append(doc.getMimeType());
		previewUrl.append("&docUrl=");
		previewUrl.append(configService.OPENKM_PREVIEW_DOWNLOAD_URL);
		previewUrl.append(";jsessionid=");
		previewUrl.append(request.getSession().getId());
		previewUrl.append("?node=");
		previewUrl.append(doc.getUuid());
		previewUrl.append("%26attachment=true");
		return previewUrl.toString();
	}

	@GetMapping("/Preview")
	public ModelAndView getPreview(@RequestParam("node") String node, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			InputStream is = okmService.getContent(node);
			Document doc = okmService.getProperties(node);
			WebUtils.prepareSendFile(request, response, doc.getActualVersion().getName(), doc.getMimeType(), true);
			response.setHeader("X-Frame-Options", "SAMEORIGIN");
			response.setContentLength(new Long(doc.getActualVersion().getSize()).intValue());
			ServletOutputStream sos = response.getOutputStream();
			IOUtils.copy(is, sos);
			sos.flush();
			sos.close();
		} catch (Exception e) {
			logger.info("Previsualización de boleta cancelada");
		}
		return null;
	}

}

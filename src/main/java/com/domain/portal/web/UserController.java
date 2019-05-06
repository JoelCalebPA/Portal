package com.domain.portal.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.domain.portal.service.OpenkmService;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.NoSuchGroupException;
import com.openkm.sdk4j.exception.ParseException;
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

	@GetMapping("/user")
	public String getHome(Model model) {
		return "user/home";
	}

	@GetMapping(path = "/user/record")
	public String getRecord(Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			model.addAttribute("records", okmService.getDocuments(auth.getName()));
		} catch (AccessDeniedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		} catch (UnknowException e) {
			e.printStackTrace();
		} catch (WebserviceException e) {
			e.printStackTrace();
		} catch (NoSuchGroupException e) {
			e.printStackTrace();
		}
		return "user/record";
	}

	@GetMapping("/user/account")
	public String getAccount() {
		return "user/account";
	}

}

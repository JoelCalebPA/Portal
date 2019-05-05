package com.domain.portal.web;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.domain.portal.model.User;

@Controller
public class UserController {

	@Autowired
	@Qualifier(value = "entityManagerFactory")
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	@Qualifier(value = "transactionManager")
	JpaTransactionManager transactionManager;

	
	@GetMapping("/user")
	public String getHome(Model model) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("select t from user t");
		model.addAttribute("user", (User)q.getResultList().get(0));
		return "user/home";
	}
	
	@GetMapping("/user/record")
	public String getRecord() {
		return "user/record";
	}
	
	@GetMapping("/user/account")
	public String getAccount() {
		return "user/account";
	}
	
}

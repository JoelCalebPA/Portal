package com.domain.portal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.portal.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IUserService userService;
	
	@GetMapping
	public ModelAndView getHome() {
		ModelAndView view;
		try {
			view = new ModelAndView("admin/home");
			view.addObject("users", userService.findAll());
		} catch (Exception e) {
			view = new ModelAndView("error");
		}
		return view;
	}

	@GetMapping("/account")
	public ModelAndView getAccount() {
		ModelAndView view;
		try {
			view = new ModelAndView("admin/account");
		} catch (Exception e) {
			view = new ModelAndView("error");
		}
		return view;
	}

}

package com.domain.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping
	public ModelAndView getHome() {
		ModelAndView view;
		try {
			view = new ModelAndView("admin/home");
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

package com.domain.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user")
	public String getHome() {
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

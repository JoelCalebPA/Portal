package com.domain.portal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.portal.model.User;
import com.domain.portal.service.UserService;
import com.domain.portal.validator.UserValidator;

@Controller
public class LoginController {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.saveUser(userForm);
//		securityService.autologin(userForm.getUsuario(), userForm.getPassword());
		return "redirect:/login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		String redirect = "redirect:/login";
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		redirect = "redirect:/user";
//		List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//				.collect(Collectors.toList());
//		for (String role : roles) {
//			if (role.equals("ROLE_USER")) {
//				redirect = "user/home";
//			} else if (role.equals("ROLE_ADMIN")) {
//				redirect = "admin/home";
//			} else if (role.equals("ROLE_ANONYMUS")) {
//				redirect = "redirect:/login";
//			}
//		}
		return redirect;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Tu usuario y contrase�a son incorrectos.");
		if (logout != null)
			model.addAttribute("message", "Cerraste sesi�n exitosamente.");
		return "login";
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect() {
		String redirect = "";
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//				.collect(Collectors.toList());
		redirect = "redirect:/user";
//		for (String role : roles) {
//			if (role.equals("ROLE_USER")) {
//				redirect = "redirect:/user";
//			} else if (role.equals("ROLE_ADMIN")) {
//				redirect = "redirect:/admin";
//			}
//		}
		return redirect;
	}

}

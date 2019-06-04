package com.domain.portal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.portal.model.User;
import com.domain.portal.service.IUserService;
import com.domain.portal.validator.UserValidator;

@Controller
public class LoginController {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private IUserService iUserService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model, Authentication auth) {
		model.addAttribute("userForm", new User());
		return redirectByRole(auth, "registration");
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		iUserService.save(userForm);
		return "redirect:/login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Authentication auth) {
		return redirectByRole(auth, "redirect:/login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Tu usuario y contraseña son incorrectos.");
		if (logout != null)
			model.addAttribute("message", "Cerraste sesión exitosamente.");
		return "login";
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect(Authentication auth) {
		return redirectByRole(auth, "redirect:/login");
	}

	private String redirectByRole(Authentication auth, String route) {
		String redirect = "";
		try {
			String role = auth.getAuthorities().iterator().next().getAuthority();
			switch (role) {
			case "ROLE_USER":
				redirect = "redirect:/user";
				break;
			case "ROLE_ADMIN":
				redirect = "redirect:/admin";
				break;
			default:
				redirect = route;
				break;
			}
		} catch (Exception e) {
			redirect = route;
		}
		return redirect;
	}

}

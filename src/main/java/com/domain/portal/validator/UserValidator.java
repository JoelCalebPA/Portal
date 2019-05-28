package com.domain.portal.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.domain.portal.dao.UsuarioDao;
import com.domain.portal.model.User;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UsuarioDao uDao;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario", "NotEmpty");
		if (user.getUsuario().length() < 6 || user.getUsuario().length() > 32) {
			errors.rejectValue("usuario", "Size.userForm.username");
		}
		if (uDao.findUser(user.getUsuario()) != null) {
			errors.rejectValue("usuario", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}
	}
}

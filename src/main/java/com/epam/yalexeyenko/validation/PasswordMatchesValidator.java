package com.epam.yalexeyenko.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.epam.yalexeyenko.dto.UserDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches arg0) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		UserDTO userDTO = (UserDTO) obj;
		return userDTO.getPassword().equals(userDTO.getMatchingPassword());
	}

}

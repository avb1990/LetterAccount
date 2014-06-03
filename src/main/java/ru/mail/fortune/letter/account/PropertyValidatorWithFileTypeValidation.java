package ru.mail.fortune.letter.account;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PropertyValidatorWithFileTypeValidation implements Validator {

	private Validator validator;

	public PropertyValidatorWithFileTypeValidation(Validator validator) {
		this.validator = validator;
	}

	public boolean supports(Class<?> arg0) {

		return Letter.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		validator.validate(arg0, arg1);

		Letter letter = (Letter) arg0;
		if (!Letter.isCorrectType(letter.getFileType()))
			arg1.reject("fileType", "Incorrect fileType");

	}
}

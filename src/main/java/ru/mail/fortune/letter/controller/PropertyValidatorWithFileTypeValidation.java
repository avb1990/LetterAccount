package ru.mail.fortune.letter.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.mail.fortune.letter.Letter;

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
			arg1.rejectValue("fileType", "fileType", "Incorrect fileType");
		if (letter.getFile() == null || letter.getFile().length == 0)
			arg1.rejectValue("file", "emptyFile");

	}
}

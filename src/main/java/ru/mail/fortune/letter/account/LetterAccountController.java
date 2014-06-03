package ru.mail.fortune.letter.account;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
public class LetterAccountController {

	@Autowired
	LettersService lettersService;

	@Autowired
	Validator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		binder.setValidator(new PropertyValidatorWithFileTypeValidation(
				validator));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/account")
	public String getLetters(HttpServletRequest request, ModelMap model) {
		model.put("letters", lettersService.getAllLetters());
		model.put("letter", new Letter());
		return "letters";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addLetter")
	public String addLetter(@Valid Letter letter, BindingResult bindingResult) {
		if (!bindingResult.hasErrors())
			lettersService.addLetter(letter);

		return "redirect:account";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getFile")
	public void getLetterFile(@RequestParam("letterId") Integer letterId,
			HttpServletResponse response) {
		if (letterId == null)
			throw new NullPointerException("Id не может быть == null");
		Letter letter = lettersService.getLetter(letterId);
		if (letter == null)
			throw new NullPointerException("Невозможно найти приказ с таким id");
		try {
			response.getOutputStream().write(letter.getFile());
			response.flushBuffer();
		} catch (IOException e) {

			throw new RuntimeException(e);
		}
		if (letter.getFileType() == ".jpg")
			response.setContentType("image/jpeg");
		else if (letter.getFileType() == ".pdf")
			response.setContentType("application/pdf");
		else
			throw new IllegalStateException("Файл неопределенного типа");
	}
}

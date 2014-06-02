package ru.mail.fortune.letter.account;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LetterAccountController {

	@Autowired
	LettersService lettersService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(byte[].class, new ByteArrayPropertyEditor());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/letters")
	public String getLetters(HttpServletRequest request, ModelMap model) {
		model.put("letters", lettersService.getAllLetters());
		model.put("letter", new Letter());
		return "letters";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addLetter")
	public String addLetter(Letter letter) {
		lettersService.addLetter(letter);
		return "redirect:/letters";
	}
}

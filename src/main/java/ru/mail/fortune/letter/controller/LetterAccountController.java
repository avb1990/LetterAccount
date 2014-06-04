package ru.mail.fortune.letter.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import ru.mail.fortune.letter.Letter;
import ru.mail.fortune.letter.service.LettersService;

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

		Letter letter = new Letter();
		initAccountModel(model, letter);
		return "letters";
	}

	private void initAccountModel(ModelMap model, Letter letter) {
		List<Letter> letters = lettersService.getAllLettersWithoutFiles();
		Collections.sort(letters, new Comparator<Letter>() {
			public int compare(Letter arg0, Letter arg1) {
				return arg1.getLetterDate().compareTo(arg0.getLetterDate());
			}
		});
		model.put("letters", letters);
		model.put("letter", letter);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addLetter")
	public String addLetter(@Valid Letter letter, BindingResult bindingResult,
			ModelMap model) {
		if (!bindingResult.hasErrors()) {
			lettersService.addLetter(letter);
			return "redirect:account";
		}
		initAccountModel(model, letter);
		return "letters";

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getFile")
	public ResponseEntity<byte[]> getLetterFile(
			@RequestParam("letterId") Integer letterId,
			HttpServletResponse response) {

		if (letterId == null)
			throw new NullPointerException();
		Letter letter = lettersService.getLetter(letterId);
		if (letter == null)
			throw new NullPointerException();
		HttpHeaders header = new HttpHeaders();
		// if (Letter.JPEG_FILE_TYPE_NAME.equals(letter.getFileType()))
		// header.setContentType(MediaType.parseMediaType("image/jpeg"));
		// else if (Letter.PDF_FILE_TYPE_NAME.equals(letter.getFileType()))
		// header.setContentType(MediaType.parseMediaType("application/pdf"));
		// else
		// throw new IllegalStateException();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		header.set("Content-Disposition", "attachment;filename=\"file"
				+ letterId.toString() + letter.getFileType() + "\" ");
		return new ResponseEntity<byte[]>(letter.getFile(), header,
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/publish")
	public String publishLetter(@RequestParam("letterId") Integer letterId) {
		if (letterId != null) {
			Letter letter = lettersService.getLetter(letterId);
			if (letter == null) {
				throw new NullPointerException();
			}
			letter.setPublished(true);
			lettersService.updateLetter(letter);
		} else
			throw new NullPointerException();
		return "redirect:account";
	}

	@ExceptionHandler(Exception.class)
	public String handleError(Exception e) {
		e.printStackTrace();
		return "error";
	}
}

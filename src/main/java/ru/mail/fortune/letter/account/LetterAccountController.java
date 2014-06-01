package ru.mail.fortune.letter.account;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/letter")
public class LetterAccountController {

	@RequestMapping(method = RequestMethod.GET)
	public String printHello(HttpServletRequest request, ModelMap model) {
		model.addAttribute("message", "Hello kitty");
		return "account";
	}
}

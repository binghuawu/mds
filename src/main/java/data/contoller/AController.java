package data.contoller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import data.domain.a.AUser;
import data.service.UserService;

@Controller
public class AController {
	private static final Logger logger = LoggerFactory
			.getLogger(AController.class);

	@Autowired
	UserService<AUser> userAService;

	@RequestMapping("/")
	public String index(Locale locale, Model model, HttpServletRequest r) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		List<AUser> users = userAService.getAll();
		model.addAttribute("users", users);
		return "home";
	}
}

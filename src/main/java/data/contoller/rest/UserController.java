package data.contoller.rest;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import data.domain.a.AUser;
import data.service.UserService;

@RestController
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	@Qualifier("u1")
	UserService<AUser> userService;

	@RequestMapping("/users")
	public List<AUser> getAllUsers(Locale locale, Model model,
			HttpServletRequest r) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return userService.getAll();
	}
}

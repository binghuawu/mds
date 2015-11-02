package data.contoller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AController {
	private static final Logger logger = LoggerFactory
			.getLogger(AController.class);

	@RequestMapping("/")
	public String index(Locale locale, Model model, HttpServletRequest r) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Long counter = (Long) r.getSession().getAttribute("counter");
		if (counter != null) {
			counter = counter.longValue() + 1;
		} else {
			counter = 1L;
		}
		r.getSession().setAttribute("counter", counter);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		// model.addAttribute("list", Arrays.asList("a", "b", "c"));
		return "home";
	}
}

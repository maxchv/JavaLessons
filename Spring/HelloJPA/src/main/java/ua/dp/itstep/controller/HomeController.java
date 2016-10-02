package ua.dp.itstep.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.dp.itstep.dao.PostService;
import ua.dp.itstep.domain.Post;
import ua.dp.itstep.repositories.PostRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//@Autowired
	//PostService postService;
	
	@Autowired
	private PostRepository repository;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
				
		
		model.addAttribute("posts", repository.findAll());
		
		return "home";
	}
	
	@RequestMapping("/{resourceId}")
	@ResponseBody
	public Post findResource(@PathVariable("resourceId") Long resourceId) {
		logger.info("Get post for id = " + resourceId);
		return repository.findOne(resourceId);		
	}
	
}

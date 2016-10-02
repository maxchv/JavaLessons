package ua.dp.itstep.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.dp.itstep.domain.Post;
import ua.dp.itstep.repositories.PostRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static Logger logger = LoggerFactory.getLogger(AdminController.class); 
	
	@Autowired
	PostRepository postRepository;
	
	@RequestMapping("/home")
	public String home(Model model){
		
		Post post = new Post();
		model.addAttribute("post", post);
		return "admin/home";
	}
	
	@RequestMapping(value="/save")
	public String save(@ModelAttribute Post post){

		postRepository.save(post);
		
		logger.info("Saved post: " + post);
		
		return "redirect:/admin/home";
	}
}

package ua.dp.itstep.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.dp.itstep.domain.Book;
import ua.dp.itstep.domain.Category;
import ua.dp.itstep.service.BookService;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/input-book")
	public String inputBook(Model model) {
		logger.info("inputBook");
		List<Category> categories = bookService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("book", new Book());
		
		return "BookAddForm";
	}
	
	@RequestMapping("/list-books")
	public String listBooks(Model model) {
		logger.info("listBooks");
		model.addAttribute("books", bookService.getAllBooks());
		return "BookList";
	}
	
	@RequestMapping("/edit-book/{id}")
	public String editBook(Model model, @PathVariable Long id) {
		logger.info("editBook");
		model.addAttribute("categories", bookService.getAllCategories());
		model.addAttribute("book", bookService.getBook(id));
		return "BookEditForm";
	}
	
	@RequestMapping("/update-book")
	public String updateBook(@ModelAttribute Book book) {
		logger.info("updateBook");
		int id = book.getCategory().getId();
		
		logger.info("Category Id: " + id);
		Category category = bookService.getCategory(id);
		book.setCategory(category);
		Book b = bookService.update(book);
		
		logger.info("Source book: " + book);
		logger.info("Update book: " + b);
		return "redirect:/list-books";
	}
	
	@RequestMapping("/save-book")
	public String saveBook(@ModelAttribute Book book) {
		logger.info("saveBook");
		Category category = bookService.getCategory(book.getCategory().getId());
		book.setCategory(category);
		bookService.save(book);
		return "redirect:/list-books";
	}
}

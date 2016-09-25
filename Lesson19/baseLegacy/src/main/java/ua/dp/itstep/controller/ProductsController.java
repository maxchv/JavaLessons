package ua.dp.itstep.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.dp.itstep.model.Product;
import ua.dp.itstep.service.ProductService;

@Controller
public class ProductsController {

	private Logger logger = LoggerFactory.getLogger(ProductsController.class); 

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/products")
	public String list(Model model) {
		logger.info("list()");
		model.addAttribute("title", "List of products");
		model.addAttribute("products", productService.getProducts());
		return "products-list";
	}
	
	@RequestMapping(value = "/products/create")
	public String create(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("title", "Create product");
		return "products-create";
	}
	
	@RequestMapping(value = "/products/save", method = RequestMethod.POST)
	public String save(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
		logger.info("saving: " + product);
		redirectAttributes.addFlashAttribute("msg", "product saved");
		logger.info("start saving");
		logger.info("saved: " + product);
		Product savedProduct = null;
		try{
			savedProduct = productService.addProduct(product);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "redirect:/products/details/" + savedProduct.getId();
	}
	
	@RequestMapping(value = "/products/details/{id}")
	public String details(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		logger.info("detalis: " + product);
		model.addAttribute("product", product);
		return "products-details";
	}
}

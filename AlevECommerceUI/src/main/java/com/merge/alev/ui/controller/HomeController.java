package com.merge.alev.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.merge.alev.ui.model.Category;
import com.merge.alev.ui.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService; 
	
	@RequestMapping({"/", "/home"})
	public String index(Model model) {
		
		Category category = new Category();
		category.setId(0);
		
		productService.getProductsByCategory(category, 0, 10);
		
		
		return "index";
	}

	@RequestMapping("/trackorder")
	public String order(Model model) {
		return "order";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		return "about";
	}
	
}

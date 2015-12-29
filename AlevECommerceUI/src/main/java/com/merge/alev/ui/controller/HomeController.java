package com.merge.alev.ui.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.merge.alev.ui.model.Category;
import com.merge.alev.ui.model.Product;
import com.merge.alev.ui.model.reponse.CategoryResponse;
import com.merge.alev.ui.model.reponse.ProductResponse;
import com.merge.alev.ui.service.CategoryService;
import com.merge.alev.ui.service.ProductService;

@Controller
public class HomeController {

	final String productsByCategory = "productsByCategory";
	final String categories = "categories";
	final String selectedCategory = "selectedCategory";
	final String serviceBaseUrl = "serviceBaseUrl";
	final String maxRowsPerPage = "maxRowsPerPage";

	@Autowired
	private ProductService productService; 
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping({"/", "/home"})
	public String index(Model model) {
		Map<String, Object> modelMap = model.asMap();

		if (!model.containsAttribute(serviceBaseUrl))
			model.addAttribute(maxRowsPerPage, 10);
			
		if (!model.containsAttribute(serviceBaseUrl))
			model.addAttribute(serviceBaseUrl, "http://localhost:8090");
		
		if (!model.containsAttribute(categories)) {
			CategoryResponse catRes = categoryService.getCategories();
			if (catRes.getResponseCode() != null && !catRes.getResponseCode().equals(-1)) {		
				model.addAttribute(categories, catRes.getModel());
				if (!catRes.getModel().isEmpty())
					model.addAttribute(selectedCategory, catRes.getModel().get(0));
			}
		}
			
		if (model.containsAttribute(productsByCategory))
			model.addAttribute(productsByCategory, new ArrayList<Product>());
			
		Category category = (Category) modelMap.get("selectedCategory");
		
//		ProductResponse prodRes = productService.getProductsByCategory(category, 0, 10);
//		if (prodRes.getResponseCode() != null && !prodRes.getResponseCode().equals(-1)) 
//			if (!prodRes.getModel().isEmpty()) 
//				model.addAttribute("productsByCategory", prodRes.getModel());
			
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
	
	@RequestMapping("/adminlogin")
	public String login(Model model) {
		return "login";
	}
	
}

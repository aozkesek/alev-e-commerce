package com.merge.alev.ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.merge.alev.ui.service.IPageService;

@Controller
public class PageController {

	@Autowired
	private IPageService homePageService;
	@Autowired
	private IPageService categoryProductsService;
	@Autowired
	private IPageService categoryPageService;
	
	
	@RequestMapping({"/layout", "/layout.html"})
	public String layout() {
		return "layout";
	}
	
	@RequestMapping({"/", "/home"})
	public String index(Model model) {
		return homePageService.process(null, model);
	}

	@RequestMapping("/trackorder")
	public String order() {
		return "order";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping("/category/products/{id}")
	public String categoryProducts(@PathVariable Integer id, HttpServletRequest request, Model model) {
		model.addAttribute("categoryId", id);
		return categoryProductsService.process(request, model);
	}
	
	@RequestMapping("/administration")
	public String administration() {
		return "administration/index";
	}
	
	@RequestMapping("/adminlogin")
	public String adminlogin() {
		return "administration/login";
	}
	
	@RequestMapping("/admincategory")
	public String admincategory(Model model) {
		return categoryPageService.process(null, model);
	}
	
	@RequestMapping("/adminfee")
	public String adminfee() {
		return "administration/fee";
	}
	
	@RequestMapping("/adminproduct")
	public String adminproduct() {
		return "administration/product";
	}
	
	@RequestMapping("/adminlogout")
	public String adminlogout(HttpServletRequest request, Model model) throws ServletException {
		request.logout();
		model.addAttribute("loggedout", true);
		return "administration/login";
	}
	
	@RequestMapping("/sessionexpired")
	public String sessionexpired() throws ServletException {
		return "sessionexpired";
	}
	
	
}

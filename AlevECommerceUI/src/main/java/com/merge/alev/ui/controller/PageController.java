package com.merge.alev.ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.merge.alev.ui.service.IPageService;

@Controller
public class PageController {

	@Autowired
	private IPageService homePageService;
	@Autowired
	private IPageService categoryPageService;
	
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
	
	@RequestMapping("/administration")
	public String administration() {
		return "adminindex";
	}
	
	@RequestMapping("/adminlogin")
	public String adminlogin() {
		return "adminlogin";
	}
	
	@RequestMapping("/adminlogout")
	public String adminlogout(HttpServletRequest request, Model model) throws ServletException {
		request.logout();
		model.addAttribute("loggedout", true);
		return "adminlogin";
	}
	
	@RequestMapping("/sessionexpired")
	public String sessionexpired() throws ServletException {
		return "sessionexpired";
	}
	
	
}

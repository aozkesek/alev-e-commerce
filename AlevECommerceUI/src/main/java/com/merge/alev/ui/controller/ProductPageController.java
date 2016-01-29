package com.merge.alev.ui.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.merge.alev.ui.service.IPageService;

@Controller
@MultipartConfig(maxFileSize=1024*1024, maxRequestSize=1024*1024*10)
public class ProductPageController {

	@Autowired
	private IPageService productPageService;
	
	@RequestMapping("/adminproduct")
	public String adminproduct(HttpServletRequest request, HttpServletResponse response, Model model) {
		return productPageService.process(request, response, model);
	}
	
}

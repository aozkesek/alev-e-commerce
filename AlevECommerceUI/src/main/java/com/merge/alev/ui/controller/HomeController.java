package com.merge.alev.ui.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolution;

@Controller
public class HomeController implements ITemplateResolver {

	public final static String NAME="index";
	
	@RequestMapping({"/", "/index", "/home"})
	public String home() {
		return NAME;
	}
	
	@Override
	public String getName() {	
		return NAME;
	}

	@Override
	public Integer getOrder() {
		return null;
	}

	@Override
	public TemplateResolution resolveTemplate(TemplateProcessingParameters templateProcessingParameters) {
		return null;
	}

	@Override
	public void initialize() {
		
	}

	public void process(
	        HttpServletRequest request
	        , HttpServletResponse response
	        , ServletContext servletContext
	        , TemplateEngine templateEngine) {
		
		
		String s = "";
		
		s = NAME;
	}
	

}

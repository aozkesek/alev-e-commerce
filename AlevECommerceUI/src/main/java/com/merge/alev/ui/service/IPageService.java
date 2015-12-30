package com.merge.alev.ui.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface IPageService {
	
	String process(HttpServletRequest request, Model model);

}

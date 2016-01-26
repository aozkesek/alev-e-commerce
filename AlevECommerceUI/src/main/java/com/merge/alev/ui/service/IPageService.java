package com.merge.alev.ui.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface IPageService {
	
	String process(HttpServletRequest request, HttpServletResponse response, Model model);

}

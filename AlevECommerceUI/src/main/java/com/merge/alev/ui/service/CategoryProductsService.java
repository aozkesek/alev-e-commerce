package com.merge.alev.ui.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component("categoryProductsService")
public class CategoryProductsService implements IPageService {

	@Override
	public String process(HttpServletRequest request, Model model) {
		Map<String, Object> modelMap = model.asMap();
		
		
		return "products";
	}

}

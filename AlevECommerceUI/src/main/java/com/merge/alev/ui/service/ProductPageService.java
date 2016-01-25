package com.merge.alev.ui.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component("productPageService")
public class ProductPageService implements IPageService {

	@Override
	public String process(HttpServletRequest request, Model model) {
		Map<String, Object> modelMap = model.asMap();
		Map<String, String[]> paramMap = request.getParameterMap();	
		String contentType = request.getContentType();
		if (contentType == null || !contentType.startsWith("multipart/form-data"))
			return "administration/product";
		
		System.out.println( paramMap.get("pictures") );
		
		return "administration/index";
	}

}

package com.merge.alev.ui.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.merge.alev.ui.model.reponse.CategoryResponse;

@Component("categoryPageService")
public class CategoryPageService implements IPageService {

	final String productsByCategory = "productsByCategory";
	final String categories = "categories";
	final String selectedCategory = "selectedCategory";
	final String serviceBaseUrl = "serviceBaseUrl";
	final String maxRowsPerPage = "maxRowsPerPage";

	@Autowired
	private CategoryService categoryService;
	
	@Override
	public String process(HttpServletRequest request, Model model) {
		Map<String, Object> modelMap = model.asMap();
			
		CategoryResponse catRes = categoryService.getCategories();
		if (catRes.getResponseCode() != null && !catRes.getResponseCode().equals(-1)) {		
			model.addAttribute(categories, catRes.getModel());
			if (!catRes.getModel().isEmpty())
				model.addAttribute(selectedCategory, catRes.getModel().get(0));
		}

		return "administration/category";
	}

}

package com.merge.alev.ui.service;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.merge.alev.ui.model.Category;
import com.merge.alev.ui.model.reponse.CategoryResponse;
import com.merge.alev.ui.model.request.CategoryRequest;

@Component
public class CategoryService {
	
	private static final String Version = "1.0.0";
	private static final String Endpoint = "http://localhost:8080/category";
	
	public CategoryResponse getCategories() {
		CategoryResponse res = new CategoryResponse();
		CategoryRequest req = new CategoryRequest();
		
		req.setFirstRecordNumber(0);
		req.setMaxRecordNumber(100);
		req.setVersionNumber(Version);;
		req.setModel(new ArrayList<Category>());
		
		RestTemplate restTemp = new RestTemplate();
		res = restTemp.postForObject(Endpoint + "/list", req, CategoryResponse.class);
		
		return res;
	}

	
}

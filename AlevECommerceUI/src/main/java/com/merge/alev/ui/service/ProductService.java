package com.merge.alev.ui.service;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.merge.alev.ui.model.Category;
import com.merge.alev.ui.model.Product;
import com.merge.alev.ui.model.reponse.ProductResponse;
import com.merge.alev.ui.model.request.ProductRequest;

@Component
public class ProductService {
	
	private static final String Version = "1.0.0";
	private static final String Endpoint = "http://localhost:8080/product";
	
	public ProductResponse getProductsByCategory(Category category, int first, int max) {
		ProductResponse res = new ProductResponse();
		ProductRequest req = new ProductRequest();
		
		req.setFirstRecordNumber(first);
		req.setMaxRecordNumber(max);
		req.setVersionNumber(Version);;
		req.setModel(new ArrayList<Product>());
		
		Product p = new Product();
		p.setCategory(category);
		
		req.getModel().add(p);
		
		RestTemplate restTemp = new RestTemplate();
		res = restTemp.postForObject(Endpoint + "/list", req, ProductResponse.class);
		
		return res;
	}

	
}

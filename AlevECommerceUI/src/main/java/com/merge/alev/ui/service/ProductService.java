package com.merge.alev.ui.service;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import com.merge.alev.ui.model.Category;
import com.merge.alev.ui.model.Product;
import com.merge.alev.ui.model.reponse.ProductResponse;
import com.merge.alev.ui.model.request.ProductRequest;

@Component
public class ProductService {
	
	public ProductResponse getProductsByCategory(Category category, int first, int max) {
		ProductResponse res = new ProductResponse();
		ProductRequest req = new ProductRequest();
		
		req.setFirstRecordNumber(first);
		req.setMaxRecordNumber(max);
		req.setVersionNumber(ServiceConstants.Version);;
		req.setModel(new ArrayList<Product>());
		
		Product p = new Product();
		p.setCategory(category);
		
		req.getModel().add(p);
		
		res = RestProxy.postForObject(ServiceConstants.ProductEndpoint + "/list", req, ProductResponse.class);
		
		return res;
	}

	public ProductResponse updateProduct(ProductRequest request) {
		ProductResponse response = new ProductResponse();
		
		
		return response;
		
	}
	
	public ProductResponse addProduct(ProductRequest request) {
		ProductResponse response = new ProductResponse();
		
		return response;
	}
}

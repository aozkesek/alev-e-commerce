package com.merge.alev.ui.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.merge.alev.ui.model.Category;
import com.merge.alev.ui.model.Product;
import com.merge.alev.ui.model.reponse.ProductResponse;
import com.merge.alev.ui.model.request.ProductRequest;

@Component("categoryProductsService")
public class CategoryProductsService implements IPageService {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> modelMap = model.asMap();
		ProductRequest prequest = new ProductRequest();
		Product product = new Product();
		Integer categoryId = (Integer)modelMap.get("categoryId");
		
		if (categoryId != -1) { //hot deals
			
			product.setCategory(new Category());
			product.getCategory().setId(categoryId);
			
			prequest.setModel(new ArrayList<Product>());
			prequest.getModel().add(product);
			
			Integer totalRecordNumber = RestProxy.postForObject(ServiceConstants.ProductEndpoint + "/listTotalRecord", prequest, Integer.class);
			model.addAttribute("totalRecordNumber", totalRecordNumber);
			
			prequest.setFirstRecordNumber(0);
			prequest.setMaxRecordNumber(10);
			
			ProductResponse presponse = RestProxy.postForObject(ServiceConstants.ProductEndpoint + "/list", prequest, ProductResponse.class);
			if (presponse.getResponseCode() > -1)
				model.addAttribute("products", presponse.getModel());
		}
		
		return "products";
	}

}

package com.merge.alev.ui.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.merge.alev.ui.model.Category;
import com.merge.alev.ui.model.Product;
import com.merge.alev.ui.model.request.ProductRequest;

@Component("categoryProductsService")
public class CategoryProductsService implements IPageService {

	@Override
	public String process(HttpServletRequest request, Model model) {
		Map<String, Object> modelMap = model.asMap();
		ProductRequest prequest = new ProductRequest();
		Product product = new Product();
		
		product.setCategory(new Category());
		product.getCategory().setId((Integer)modelMap.get("categoryId"));
		
		prequest.setModel(new ArrayList<Product>());
		prequest.getModel().add(product);
		
		Integer totalRecordNumber = RestProxy.postForObject(ServiceConstants.ProductEndpoint + "/listTotalRecord", prequest, Integer.class);
		model.addAttribute("responseCode", 0);
		model.addAttribute("totalRecordNumber", totalRecordNumber);
		
		return "products";
	}

}

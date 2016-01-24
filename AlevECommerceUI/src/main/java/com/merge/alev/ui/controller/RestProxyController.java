package com.merge.alev.ui.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.merge.alev.ui.model.reponse.CategoryResponse;
import com.merge.alev.ui.model.reponse.ProductResponse;
import com.merge.alev.ui.model.request.CategoryRequest;
import com.merge.alev.ui.model.request.ProductRequest;
import com.merge.alev.ui.service.RestProxy;
import com.merge.alev.ui.service.ServiceConstants;

@RestController
public class RestProxyController {
	
	@RequestMapping("/product/list")
	@ResponseBody
	public ProductResponse proxiedProductList(@RequestBody ProductRequest request) {
		ProductResponse response = RestProxy.postForObject(ServiceConstants.ProductEndpoint + "/list", request, ProductResponse.class);
		return response;
	}
	
	@RequestMapping("/product/listTotalRecord")
	@ResponseBody
	public ProductResponse proxiedProduct(@RequestBody ProductRequest request) {
		ProductResponse response = new ProductResponse();
		response.setTotalRecordNumber(RestProxy.postForObject(ServiceConstants.ProductEndpoint + "/listTotalRecord", request, Integer.class));
		response.setResponseCode(0);
		return response;
	}

	@RequestMapping("/category/list")
	@ResponseBody
	public CategoryResponse proxiedCategory() {
		CategoryRequest request = new CategoryRequest();
		request.setFirstRecordNumber(0);
		request.setMaxRecordNumber(100);
		CategoryResponse response = RestProxy.postForObject(ServiceConstants.CategoryEndpoint + "/list", request, CategoryResponse.class);
		return response;
	}
	
	@RequestMapping("/administration/category/{addUpdate}")
	@ResponseBody
	public CategoryResponse proxiedCategoryAddUpdate(@PathVariable String addUpdate, @RequestBody CategoryRequest request) {
		CategoryResponse response = RestProxy.postForObject(ServiceConstants.CategoryEndpoint + "/" + addUpdate, request, CategoryResponse.class);
		return response;
	}
	
	@RequestMapping("/administration/product/{addUpdate}")
	@ResponseBody
	public ProductResponse proxiedProductAddUpdate(@PathVariable String addUpdate, @RequestBody ProductRequest request) {
		ProductResponse response = RestProxy.postForObject(ServiceConstants.ProductEndpoint + "/" + addUpdate, request, ProductResponse.class);
		return response;
	}
	
		
}

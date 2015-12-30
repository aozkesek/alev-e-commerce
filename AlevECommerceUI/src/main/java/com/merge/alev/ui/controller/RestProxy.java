package com.merge.alev.ui.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.merge.alev.ui.model.reponse.CategoryResponse;
import com.merge.alev.ui.model.reponse.ProductResponse;
import com.merge.alev.ui.model.request.CategoryRequest;
import com.merge.alev.ui.model.request.ProductRequest;

@RestController
public class RestProxy {
	private static final String Endpoint = "http://localhost:8080";

	@RequestMapping("/product/list")
	@ResponseBody
	public ProductResponse proxiedProductList(@RequestBody ProductRequest request) {
		RestTemplate restTemp = new RestTemplate();
		ProductResponse response = null;
		response = restTemp.postForObject(Endpoint + "/product/list", request, ProductResponse.class);
		return response;
	}
	
	@RequestMapping("/product/listTotalRecord")
	@ResponseBody
	public ProductResponse proxiedProduct(@RequestBody ProductRequest request) {
		RestTemplate restTemp = new RestTemplate();
		ProductResponse response = null;
		response = new ProductResponse();
		response.setTotalRecordNumber(restTemp.postForObject(Endpoint + "/product/listTotalRecord", request, Integer.class));
		response.setResponseCode(0);
		return response;
	}

	@RequestMapping("/category/list")
	@ResponseBody
	public CategoryResponse proxiedCategory() {
		RestTemplate restTemp = new RestTemplate();
		CategoryResponse response = null;
		CategoryRequest request = new CategoryRequest();
		request.setFirstRecordNumber(0);
		request.setMaxRecordNumber(100);
		response = restTemp.postForObject(Endpoint + "/category/list", request, CategoryResponse.class);
		return response;
	}
	
		
}

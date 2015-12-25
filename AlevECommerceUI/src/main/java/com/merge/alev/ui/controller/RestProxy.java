package com.merge.alev.ui.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.merge.alev.ui.model.reponse.ProductResponse;
import com.merge.alev.ui.model.request.ProductRequest;

@RestController
public class RestProxy {
	private static final String Endpoint = "http://localhost:8080";

	@RequestMapping("/product/{operation}")
	@ResponseBody
	public ProductResponse proxiedProduct(
			@PathVariable("operation") String operation
			, @RequestBody ProductRequest request) 
	{
		RestTemplate restTemp = new RestTemplate();
		ProductResponse response = restTemp.postForObject(Endpoint + "/product/" + operation, request, ProductResponse.class);
		return response;
	}
}

package com.merge.alev.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.controller.request.ProductCommandRequest;
import com.merge.alev.controller.response.ProductCommandResponse;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
public class ProductCommandController {
	
	
	@RequestMapping("/productcommand/create")
	@ResponseBody
	public ProductCommandResponse create(@RequestBody ProductCommandRequest request) {
		ProductCommandResponse response = new ProductCommandResponse();
		
		
		return response;
	}

}

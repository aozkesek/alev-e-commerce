package com.merge.alev.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.controller.request.ProductCommandRequest;
import com.merge.alev.controller.response.ProductCommandResponse;
import com.merge.alev.dao.model.ProductCommand;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
public class ProductCommandController {
	
	final static String ARGUMENTISNULL = "ArgumentIsNullException";
	
	@Autowired
	private IGenericDAO<ProductCommand> productCommandDao;
	
	public IGenericDAO<ProductCommand> getProductCommandDao() {
		return productCommandDao;
	}

	public void setProductCommandDao(IGenericDAO<ProductCommand> productCommandDao) {
		this.productCommandDao = productCommandDao;
	}

	@RequestMapping("/productcommand/create")
	@ResponseBody
	public ProductCommandResponse create(@RequestBody ProductCommandRequest request) {
		ProductCommandResponse response = new ProductCommandResponse();
		
		response.setModel(new ArrayList<ProductCommand>());
		response.setResponseMesage(new ArrayList<String>());
		
		try {
		
			assert request == null : ARGUMENTISNULL;
			assert request.getModel() == null : ARGUMENTISNULL;
			assert request.getModel().isEmpty() : ARGUMENTISNULL;
			
			for (ProductCommand pc : request.getModel()) {
				
				try {
					response.getModel().add(getProductCommandDao().create(pc));
						
				}
				catch(Exception ex) {
					response.getResponseMesage().add(ex.getMessage());
					
				}
				
			}
			
			
		}
		catch(Exception ex) {
			
		}
		
		return response;
	}

}

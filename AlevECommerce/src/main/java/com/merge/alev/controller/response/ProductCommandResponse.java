package com.merge.alev.controller.response;

import java.util.List;

import com.merge.alev.controller.model.ProductCommand;
import com.merge.base.controller.BaseRequest;

public class ProductCommandResponse extends BaseRequest {

	private List<ProductCommand> model;

	public List<ProductCommand> getModel() {
		return model;
	}

	public void setModel(List<ProductCommand> model) {
		this.model = model;
	}
	
	
	
}

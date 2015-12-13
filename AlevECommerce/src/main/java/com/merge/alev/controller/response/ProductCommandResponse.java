package com.merge.alev.controller.response;

import java.util.List;

import com.merge.alev.dao.model.ProductCommand;
import com.merge.base.controller.BaseResponse;

public class ProductCommandResponse extends BaseResponse {

	private List<ProductCommand> model;

	public List<ProductCommand> getModel() {
		return model;
	}

	public void setModel(List<ProductCommand> model) {
		this.model = model;
	}
	
	
	
}

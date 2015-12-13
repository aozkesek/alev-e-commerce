package com.merge.alev.controller.request;

import java.io.Serializable;
import java.util.List;

import com.merge.alev.dao.model.ProductCommand;
import com.merge.base.controller.BaseRequest;

public class ProductCommandRequest extends BaseRequest implements Serializable {

	private List<ProductCommand> model;

	public List<ProductCommand> getModel() {
		return model;
	}

	public void setModel(List<ProductCommand> model) {
		this.model = model;
	}
	
	
}

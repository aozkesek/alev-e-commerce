package com.merge.base.controller;

import java.util.List;

import com.merge.base.dao.model.AbstractModel;

public class GenericRequest<T extends AbstractModel> extends BaseRequest {

	private List<T> model;
	
	public List<T> getModel() {
		return model;
	}
	
	public void setModel(List<T> model) {
		this.model = model;
	}
	
	
}

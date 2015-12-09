package com.merge.base.controller;

import java.util.List;

import com.merge.base.dao.model.AbstractModel;

public class GenericResponse<T extends AbstractModel> extends BaseResponse {

	private List<T> model;

	public List<T> getModel() {
		return model;
	}

	public void setModel(List<T> model) {
		this.model = model;
	}
	
	

}

package com.merge.alev.ui.model.request;

import java.util.List;

public class GenericRequest<T> extends BaseRequest {

	private List<T> model;
	
	public List<T> getModel() {
		return model;
	}
	
	public void setModel(List<T> model) {
		this.model = model;
	}
	
	
}

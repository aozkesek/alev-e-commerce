package com.merge.alev.ui.model.reponse;

import java.util.List;

public class GenericResponse<T> extends BaseResponse {

	private List<T> model;

	public List<T> getModel() {
		return model;
	}

	public void setModel(List<T> model) {
		this.model = model;
	}
	
	

}

package com.merge.alev.controller;

import java.util.List;

import com.merge.alev.dao.model.AbstractModel;

public class GenericResponse<T extends AbstractModel> {

	private Integer firstRecordNumber;
	private Integer totalRecordNumber;
	private Integer responseCode;
	private List<String> responseMesage;	
	private String versionNumber;
	
	private List<T> model;

	public Integer getFirstRecordNumber() {
		return firstRecordNumber;
	}

	public void setFirstRecordNumber(Integer firstRecordNumber) {
		this.firstRecordNumber = firstRecordNumber;
	}

	public Integer getTotalRecordNumber() {
		return totalRecordNumber;
	}

	public void setTotalRecordNumber(Integer totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public List<String> getResponseMesage() {
		return responseMesage;
	}

	public void setResponseMesage(List<String> responseMesage) {
		this.responseMesage = responseMesage;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public List<T> getModel() {
		return model;
	}

	public void setModel(List<T> model) {
		this.model = model;
	}
	
	

}

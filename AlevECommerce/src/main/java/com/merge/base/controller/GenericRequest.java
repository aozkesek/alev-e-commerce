package com.merge.base.controller;

import java.util.List;

import com.merge.base.dao.model.AbstractModel;

public class GenericRequest<T extends AbstractModel> {

	private String versionNumber;
	private Integer firstRecordNumber;
	private Integer maxRecordNumber;
	private List<T> model;
	
	public Integer getFirstRecordNumber() {
		return firstRecordNumber;
	}
	
	public void setFirstRecordNumber(Integer firstRecordNumber) {
		this.firstRecordNumber = firstRecordNumber;
	}
	
	public Integer getMaxRecordNumber() {
		return maxRecordNumber;
	}
	
	public void setMaxRecordNumber(Integer maxRecordNumber) {
		this.maxRecordNumber = maxRecordNumber;
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

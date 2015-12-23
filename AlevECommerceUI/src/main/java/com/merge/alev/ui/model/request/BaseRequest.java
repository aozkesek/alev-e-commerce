package com.merge.alev.ui.model.request;

public class BaseRequest {

	private String versionNumber;
	private Integer firstRecordNumber;
	private Integer maxRecordNumber;
	
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
	
}

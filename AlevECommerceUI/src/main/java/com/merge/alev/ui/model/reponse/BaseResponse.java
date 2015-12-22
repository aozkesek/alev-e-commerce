package com.merge.alev.ui.model.reponse;

import java.util.List;

public class BaseResponse {

	private Integer firstRecordNumber;
	private Integer totalRecordNumber;
	private Integer responseCode;
	private List<String> responseMesage;	
	private String versionNumber;

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

}

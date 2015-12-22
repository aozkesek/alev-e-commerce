package com.merge.alev.ui.model;

public class Category {

	private Integer id;
	private Integer version;
	private String categoryName;
	
	public Category() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		return String.format("{id=%d, categoryName=%s}", getId(), getCategoryName());
	}

}

package com.merge.alev.ui.model;

public class ProductPicture {
	
	private Integer id;
	private Integer version;
	private String name;
	private String path;

	public ProductPicture() {
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return String.format("{id=%d, name=%s, path=%s}"
				, id, name, path);
	}
	
}

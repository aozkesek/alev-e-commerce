package com.merge.alev.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.merge.base.dao.model.AbstractModel;

@Entity
@Table(name="PRODUCTPICTURES", schema="ALEVECOM")
public class ProductPicture extends AbstractModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@Version
	private Integer version;
	@Column(name="PRODUCTID")
	private Integer productId;
	@Column(name="NAME")
	private String name;
	@Column(name="PATH")
	private String path;
	
	public ProductPicture() {
		
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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
		return String.format("{id=%d, productId=%s, name=%s, path=%s}"
				, getId(), getProductId(), getName(), getPath());
	}
	
}

package com.merge.alev.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Proxy;

import com.merge.base.dao.model.AbstractModel;

@Entity
@Proxy(lazy=false)
@Table(name="CATEGORIES", schema="ALEVECOM")
public class Category extends AbstractModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Version
	@Column(name="VERSION")
	private Integer version;
	
	@Column(name="CATEGORYNAME")
	private String categoryName;
	
	public Category() {
		
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

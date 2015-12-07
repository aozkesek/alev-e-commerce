package com.merge.alev.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.merge.base.dao.model.AbstractModel;

@Entity
@Table(name="PRODUCTS", schema="ALEVECOM")
public class Product extends AbstractModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@Column(name="CATEGORYID")
	private Integer categoryId;
	@Column(name="NAME")
	private String name;
	@Column(name="TITLE")
	private String title;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="COLORS")
	private String colors;
	@Column(name="SIZES")
	private String sizes;
	@Column(name="PRICE")
	private Double price;
	@Column(name="ACTUALPRICE")
	private Double actualPrice;
	@Column(name="CREATEDATE")
	private Date createDate;
	@Column(name="UPDATEDATE")
	private Date updateDate;
	
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return String.format("{id=%d, categoryId=%s, name=%s, title=%s, description=%s, colors=%s, sizes=%s, price=%f, actualPrice=%f, createDate=%t, updateDate=%t}"
				, getId(), getCategoryId(), getName(), getTitle(), getDescription(), getColors(), getSizes(), getPrice(), getActualPrice(), getCreateDate(), getUpdateDate());
	}
	
}

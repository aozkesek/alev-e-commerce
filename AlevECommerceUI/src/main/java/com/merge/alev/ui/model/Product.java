package com.merge.alev.ui.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {
	
	protected Integer id;
	protected Integer version;
	protected String name;
	protected String title;
	protected String description;
	protected String colors;
	protected String sizes;
	protected Double price;
	protected Double actualPrice;
	protected Date createDate;
	protected Date updateDate;
	protected Category category;
	protected List<ProductPicture> pictures;
	
	public Product() {
		setPictures(new ArrayList<ProductPicture>());
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
	
	public List<ProductPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<ProductPicture> pictures) {
		this.pictures = pictures;
	}

	@Override
	public String toString() {
		return String.format("{id=%d, categoryId=%s, name=%s, title=%s, description=%s, colors=%s, sizes=%s, price=%f, actualPrice=%f, createDate=%c, updateDate=%c}"
				, id, category.getId(), name, title, description, colors, sizes, price, actualPrice, createDate, updateDate);
	}

	
}

package com.merge.alev.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Proxy;

import com.merge.base.dao.model.AbstractModel;

@Entity
@Proxy(lazy=false)
@Table(name="PRODUCTS", schema="ALEVECOM")
public class Product extends AbstractModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	protected Integer id;
	
	@Version
	@Column(name="VERSION")
	protected Integer version;
	
	@Column(name="NAME")
	protected String name;
	
	@Column(name="TITLE")
	protected String title;
	
	@Column(name="DESCRIPTION")
	protected String description;
	
	@Column(name="COLORS")
	protected String colors;
	
	@Column(name="SIZES")
	protected String sizes;
	
	@Column(name="PRICE")
	protected Double price;
	
	@Column(name="ACTUALPRICE")
	protected Double actualPrice;
	
	@Column(name="CREATEDATE")
	protected Date createDate;
	
	@Column(name="UPDATEDATE")
	protected Date updateDate;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="CATEGORY_ID", referencedColumnName="ID")
	protected Category category;
	
	@OneToMany(targetEntity=ProductPicture.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")
	protected List<ProductPicture> pictures;
	
	public Product() {
		setPictures(new ArrayList<ProductPicture>());
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

	@Override
	public boolean isValid() {
		return id != null 
				&& category != null && category.getId() != null
				&& name != null && !name.isEmpty()
				&& title != null && !title.isEmpty();
	}
	
}

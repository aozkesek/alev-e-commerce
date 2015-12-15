package com.merge.alev.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merge.base.dao.model.AbstractModel;

@Entity
@Proxy(lazy=false)
@Table(name="PRODUCTPICTURES", schema="ALEVECOM")
public class ProductPicture extends AbstractModel implements Serializable {
	
	@Transient
	public static int MAX_PICTURE = 8;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Version
	@Column(name="VERSION")
	private Integer version;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PATH")
	private String path;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")
	@JsonIgnore
	protected Product product;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
				, id, product.getId(), name, path);
	}
	
}

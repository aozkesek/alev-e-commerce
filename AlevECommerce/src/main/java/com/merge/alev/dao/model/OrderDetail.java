package com.merge.alev.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="ORDERDETAILS", schema="ALEVECOM")
public class OrderDetail extends AbstractModel {
	
	@Transient
	public static int MAX_DETAIL = 8;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Version
	@Column(name="VERSION")
	private Integer version;
	
	@Column(name="SIZE")
	private String size;
	
	@Column(name="COLOR")
	private String color;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="TOTALPRICE")
	private Double totalPrice;
	
	@Column(name="ACTUALPRICE")
	private Double actualPrice;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ORDER_ID", referencedColumnName="ID")
	@JsonIgnore
	protected Order order;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")
	@JsonIgnore
	protected Product product;
	
	public OrderDetail() {
		
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return String.format("{id=%d, orderId=%d, productId=%d, size=%s, color=%s, quantity=%d, totalPrice=%f, actualPrice=%f}"
				, id, order.getId(), product.getId(), size, color, quantity, totalPrice, actualPrice);
	}
	
	@Override
	public boolean isValid() {
		return id != null 
				&& order != null && order.getId() != null 
				&& product != null && product.getId() != null;
	}
}

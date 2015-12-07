package com.merge.alev.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.merge.base.dao.model.AbstractModel;

@Entity
@Table(name="ORDERDETAILS", schema="ALEVECOM")
public class OrderDetail extends AbstractModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@Column(name="ORDERID")
	private Integer orderId;
	@Column(name="PRODUCTID")
	private Integer productId;
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
	
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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
				, getId(), getOrderId(), getProductId(), getSize(), getColor(), getQuantity(), getTotalPrice(), getActualPrice());
	}
	
}

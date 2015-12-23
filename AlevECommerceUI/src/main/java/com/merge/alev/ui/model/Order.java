package com.merge.alev.ui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	
	private Integer id;
	private Integer version;
	private String orderNo;
	private String customerName;
	private String customerMiddle;
	private String customerLastname;
	private String customerTelNo;
	private String customerEmail;
	private String postCode;
	private String deliverTo;
	private String deliveryAddress;
	private Double totalAmount;
	private Double totalFees;
	private OrderStatus orderStatus;
	private PaymentStatus paymentStatus;
	private String trackNumber;
	private Date createDate;
	protected List<OrderDetail> details;
	
	public Order() {
		details = new ArrayList<OrderDetail>();
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
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMiddle() {
		return customerMiddle;
	}

	public void setCustomerMiddle(String customerMiddle) {
		this.customerMiddle = customerMiddle;
	}

	public String getCustomerLastname() {
		return customerLastname;
	}

	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}

	public String getCustomerTelNo() {
		return customerTelNo;
	}

	public void setCustomerTelNo(String customerTelNo) {
		this.customerTelNo = customerTelNo;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getDeliverTo() {
		return deliverTo;
	}

	public void setDeliverTo(String deliverTo) {
		this.deliverTo = deliverTo;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return String.format("{id=%d, orderNo=%s, customerName=%s, customerMiddle=%s, customerLastname=%s, customerTelNo=%s, customerEmail=%s, deliverTo=%s, deliveryAddress=%s, postCode=%s, totalAmount=%f, totalFees=%f, orderStatus=%s, paymentStatus=%s, trackNumber=%s, createDate=%t}"
				, getId(), getOrderNo(), getCustomerName(), getCustomerMiddle(), getCustomerLastname(), getCustomerTelNo(), getCustomerEmail(), getDeliverTo(), getDeliveryAddress(), getPostCode(), getTotalAmount(), getTotalFees(), getOrderStatus(), getPaymentStatus(), getTrackNumber(), getCreateDate());
	}


}

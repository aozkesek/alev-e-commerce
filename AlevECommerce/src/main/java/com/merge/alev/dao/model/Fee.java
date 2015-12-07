package com.merge.alev.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.merge.base.dao.model.AbstractModel;

@Entity
@Table(name="FESS", schema="ALEVECOM")
public class Fee extends AbstractModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@Column(name="POSTCODE")
	private String postCode;
	@Column(name="NAME")
	private String name;
	@Column(name="AMOUNT")
	private Double amount;
	
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return String.format("{id=%d, postCode=%s, name=%s, amount=%f}"
				, getId(), getPostCode(), getName(), getAmount());
	}
	

}

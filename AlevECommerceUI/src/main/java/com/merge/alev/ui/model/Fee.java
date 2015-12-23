package com.merge.alev.ui.model;

public class Fee {
	private Integer id;
	private Integer version;
	private String name;
	private Double amount;
	
	public Fee() {
		
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
		return String.format("{id=%d, name=%s, amount=%f}"
				, getId(), getName(), getAmount());
	}
	
}

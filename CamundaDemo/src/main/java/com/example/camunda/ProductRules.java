package com.example.camunda;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.OptBoolean;

@JsonInclude(value = Include.NON_EMPTY)
public class ProductRules implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private String policyNumber;
	@JacksonInject(value = "address", useInput = OptBoolean.FALSE)
	@JsonRawValue
	private Object address = "nn";

	public ProductRules() {
		// TODO Auto-generated constructor stub
	}

	
	public ProductRules(String productName, String policyNumber, String address) {
		super();
		this.productName = productName;
		this.policyNumber = policyNumber;
		this.address = address;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Object getAddress() {
		return address;
	}
	public void setAddress(Object address) {
		this.address = address;
	}

}

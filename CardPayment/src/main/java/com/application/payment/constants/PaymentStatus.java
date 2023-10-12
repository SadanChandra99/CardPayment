package com.application.payment.constants;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentStatus implements Serializable{

//	@JsonProperty("success")
	SUCCESS,
//	@JsonProperty("failure")
	FAILURE,
//	@JsonProperty("pending")
	PENDING;
}

package com.application.payment.response;

import org.springframework.http.HttpStatus;

import com.application.payment.entity.Payment;

public class PaymentResponseBody {
	
	private HttpStatus httpStatus;
	
	private String message;
	
	private Payment payment;

	public PaymentResponseBody(HttpStatus httpStatus, String message, Payment payment) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.payment = payment;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	

}

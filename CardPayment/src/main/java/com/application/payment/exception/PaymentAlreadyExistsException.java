package com.application.payment.exception;

public class PaymentAlreadyExistsException extends RuntimeException{

	public PaymentAlreadyExistsException(String msg) {
		super(msg);
	}

}

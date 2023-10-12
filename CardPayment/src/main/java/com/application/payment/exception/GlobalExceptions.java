package com.application.payment.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.application.payment.response.ValidationErrorResponse;

@ControllerAdvice
public class GlobalExceptions {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(HttpStatus.BAD_REQUEST,
				"Validation error", null);
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		validationErrorResponse.setErrors(errors);
		return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(PaymentAlreadyExistsException.class)
	public String paymentExistsException(PaymentAlreadyExistsException ex) {
		return "Payment Already Exists";
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public String paymentNotFoundException(PaymentNotFoundException ex) {
		return "Payment Not Found";
	}

}

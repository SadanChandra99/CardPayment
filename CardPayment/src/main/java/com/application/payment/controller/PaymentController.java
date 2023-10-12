package com.application.payment.controller;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.payment.constants.ErrorConstants;
import com.application.payment.entity.Payment;
import com.application.payment.exception.PaymentAlreadyExistsException;
import com.application.payment.exception.PaymentNotFoundException;
import com.application.payment.response.PaymentResponseBody;
import com.application.payment.response.ResponseHandler;
import com.application.payment.service.PaymentService;

import datadog.trace.api.Trace;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import datadog.trace.api.Trace;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment/card")
@Tag(name = "CardPayment", description = "the CardPayment API")
@OpenAPIDefinition(
		info = @Info(
				title = "Payment API",
				description = "Backend Payment RestAPI",
				version = "v1.0"))
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Operation(
			operationId = "savePayment",
			responses = {
					@ApiResponse(responseCode = "200",description = "paymentResponse", content = {
							@Content(mediaType = "application/json", schema = 
									@Schema(implementation = Payment.class))
					}),
					@ApiResponse(responseCode = "500", description = "Cannot Save Payment", content = {
					@Content(mediaType = "application/json", schema = 
					@Schema(implementation = Payment.class))
			})
			}
			)
	@Trace
	@PostMapping("/")
	public ResponseEntity<Payment> addPayment( @Valid @RequestBody Payment payment){

		Optional<Payment> addPayment = paymentService.addPayment(payment);
		
		if(addPayment!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(addPayment.get());
		}
		else {
			throw new PaymentAlreadyExistsException(ErrorConstants.PAYMENT_ALREADY_EXISTS);
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		
		
	}
	
	@Operation(
			operationId = "getPayment" ,
			responses = {
					@ApiResponse(responseCode = "200", description = "paymentResponse", content = {
							@Content(mediaType = "application/json",schema = 
									@Schema(implementation = Payment.class))
					}),
					@ApiResponse(responseCode = "404", description = "Not Found", content = {
							@Content(mediaType = "application/json", schema = 
									@Schema(implementation = Payment.class))
					})
			}
			
			)
	
	@Trace
	@GetMapping("/")
	public ResponseEntity<Payment> getPaymentById(@RequestParam(name = "id") String id){
		Optional<Payment> payment = paymentService.getPaymentById(id);
		if(payment.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(payment.get());
		}
		else {
			throw new PaymentNotFoundException(ErrorConstants.PAYMENT_NOT_FOUND);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/id")
	public ResponseEntity<Object> getPaymentByPaymentId(@RequestParam(name = "id") String id){
		Optional<Payment> payment = paymentService.getPaymentById(id);
		if(payment.isPresent()) {
			return ResponseHandler.generateResponse("Payment Response Success", HttpStatus.OK, payment.get());
		}
		else {
			return ResponseHandler.generateResponse("Payment Response Error", HttpStatus.NOT_FOUND, new Payment());
		}
	}
	
	@Operation(
			operationId = "updatePayment",
			responses = {
					@ApiResponse(responseCode = "200", description = "paymentResponse", content = {
							@Content(mediaType = "application/json", schema = 
									@Schema(implementation = Payment.class))
					}),
					@ApiResponse(responseCode = "404", description = "Not Found", content = {
							@Content(mediaType = "application/json", schema = 
									@Schema(implementation = Payment.class))
					}),
					@ApiResponse(responseCode = "500", description = "Cannot Update", content = {
							@Content(mediaType = "application/json", schema = 
									@Schema(implementation = Payment.class))
					})
			}
			)
	
	@Trace
	@PutMapping("/")
	public ResponseEntity<Payment> updatePayment(@Valid @RequestParam(name = "email") String email, @RequestBody Payment payment){
		Optional<Payment> updatePayment = paymentService.updatePayment(email, payment);
		if(updatePayment.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(updatePayment.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@Operation(
			operationId = "getAllPayment" ,
			responses = {
					@ApiResponse(responseCode = "200", description = "paymentResponse", content = {
							@Content(mediaType = "application/json",schema = 
									@Schema(implementation = Payment.class))
					}),
					@ApiResponse(responseCode = "404", description = "Not Found", content = {
							@Content(mediaType = "application/json", schema = 
									@Schema(implementation = Payment.class))
					})
			}
			
			)
	
	@Trace
	@GetMapping("/all")
	public ResponseEntity<List<Payment>> getPaymentsByEmail(@RequestParam(name = "email") String email){
		Optional<List<Payment>> payment = paymentService.getPaymentsByEmail(email);
		if(payment.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(payment.get());
		}
		else {
			throw new PaymentNotFoundException(ErrorConstants.PAYMENT_NOT_FOUND);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}

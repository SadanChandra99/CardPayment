package com.application.payment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.application.payment.entity.Payment;

@Service
public interface PaymentService {

	public Optional<Payment> addPayment(Payment payment);
	
	public Optional<List<Payment>> getAllPayments();
	
	public Optional<Payment> getPaymentById(String id);
	
	public Optional<Payment> updatePayment(String email, Payment payment);
	
	public Optional<List<Payment>> getPaymentsByEmail(String email);
	
	
}

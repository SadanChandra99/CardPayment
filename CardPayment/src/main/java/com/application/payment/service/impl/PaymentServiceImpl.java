package com.application.payment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.payment.constants.ErrorConstants;
import com.application.payment.entity.Payment;
import com.application.payment.exception.PaymentAlreadyExistsException;
import com.application.payment.exception.PaymentNotFoundException;
import com.application.payment.repository.PaymentRepository;
import com.application.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Optional<Payment> addPayment(Payment payment){
		// TODO Auto-generated method stub

		return Optional.of(paymentRepository.save(payment));

	}

	@Override
	public Optional<List<Payment>> getAllPayments() {
		// TODO Auto-generated method stub

		List<Payment> allPayments = paymentRepository.findAll();
		if(allPayments.isEmpty()) {
			throw new PaymentNotFoundException(ErrorConstants.PAYMENT_NOT_FOUND);
		}
		else {
			return Optional.of(paymentRepository.findAll());
		}
		
	}

//	@Override
//	public Optional<Payment> getPaymentById(String id)  {
//		// TODO Auto-generated method stub
//		Optional<Payment> payment = paymentRepository.findById(id);
//		if(payment.isEmpty()) {
//			throw new PaymentNotFoundException(String.format(ErrorConstants.PAYMENT_NOT_FOUND, id));
//		}
//		else {
//			return paymentRepository.findById(id);
//		}
//		
//	}
	
	@Override
	public Optional<Payment> getPaymentById(String id)  {
		// TODO Auto-generated method stub
//		Optional<Payment> payment = paymentRepository.findById(id);
		return paymentRepository.findById(id);
	}

	@Override
	public Optional<Payment> updatePayment(String email, Payment payment)  throws PaymentNotFoundException{
		// TODO Auto-generated method stub
		if(paymentRepository.existsById(payment.getPaymentId())) {
			Payment updatePayment = paymentRepository.findById(payment.getPaymentId()).get();
			updatePayment.setPaymentStatus(payment.getPaymentStatus());
			paymentRepository.save(updatePayment);
			return Optional.of(updatePayment);
		}
		throw new PaymentNotFoundException(ErrorConstants.PAYMENT_NOT_FOUND);
	}

	@Override
	public Optional<List<Payment>> getPaymentsByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<List<Payment>> payments = paymentRepository.findByUserEmail(email);
		return payments;
	}

}

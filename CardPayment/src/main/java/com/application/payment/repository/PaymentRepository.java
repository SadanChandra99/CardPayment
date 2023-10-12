package com.application.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String>{
	
	

	public Optional<List<Payment>> findByUserEmail(String email); 
	
	public Optional<Payment> findByCardNumber(Long number);
	
	public boolean existsByUserEmail(String email);
	
	
}

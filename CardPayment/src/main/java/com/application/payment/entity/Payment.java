package com.application.payment.entity;

import java.time.LocalDate;

import com.application.payment.constants.PaymentStatus;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "payment_id")
	private String paymentId;
	
	@Column(name = "user_email")
	@NotEmpty(message = "User Email Cannot be Empty")
	private String userEmail;
	
	@Column(name = "cardholder_name")
	@NotEmpty(message = "Card Holder Name Cannot be Empty")
	private String cardHolderName; 
	
	@Column(name = "card_number")
	@NotNull(message = "Card Number Cannot be Empty")
	private Long cardNumber;
	
	@Column(name = "Expirydate")
	@NotNull(message = "Expiry Date Cannot be Empty")
	private LocalDate expiryDate;
	
	@Column(name = "cvv")
	@NotNull(message = "CVV Cannot be Empty")
	private Integer cvv;

	@Column(name = "total_amount")
	@NotNull(message = "Amount to be paid Cannot be Empty")
	private Double totalAmount;
	
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	
	public Payment(String paymentId, @NotEmpty(message = "User Email Cannot be Empty") String userEmail,
			@NotEmpty(message = "Card Holder Name Cannot be Empty") String cardHolderName,
			@NotNull(message = "Card Number Cannot be Empty") Long cardNumber,
			@NotNull(message = "Expiry Date Cannot be Empty") LocalDate expiryDate,
			@NotNull(message = "CVV Cannot be Empty") Integer cvv,
			@NotNull(message = "Amount to be paid Cannot be Empty") Double totalAmount,
			PaymentStatus paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.userEmail = userEmail;
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
	}
	

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", userEamil=" + userEmail + ", cardHolderName=" + cardHolderName
				+ ", cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + ", cvv=" + cvv + ", totalAmount="
				+ totalAmount + ", paymentStatus=" + paymentStatus + "]";
	}
	
	
}

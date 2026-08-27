package com.transaction.spin.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "PROVIDER_TRANSACTION_ID")
	private String providerTransactionId;
	
	@Column(name = "BALANCE_AFTER")
	private Double balanceAfter;
	
	@Column(name = "CREATED_AT")
	private String createdAt; 

}

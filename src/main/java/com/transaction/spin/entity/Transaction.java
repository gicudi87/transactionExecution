package com.transaction.spin.entity;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Table("TRANSACTION")
@Getter
@Setter
public class Transaction {
	
	@Id
	@Column("ID")
	private UUID uuid;
	
	@Column("ACCOUNT_ID")
	private String accountId;
	
	@Column("TYPE")
	private String type;
	
	@Column("AMOUNT")
	private Double amount;
	
	@Column("CURRENCY")
	private String currency;
	
	@Column("DESCRIPTION")
	private String description;
	
	@Column("STATUS")
	private String status;
	
	@Column("PROVIDER_TRANSACTION_ID")
	private String providerTransactionId;
	
	@Column("BALANCE_AFTER")
	private Double balanceAfter;
	
	@Column("CREATED_AT")
	private String createdAt; 
	
	@Column("CODE")
	private String code;
	
}

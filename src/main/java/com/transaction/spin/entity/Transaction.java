package com.transaction.spin.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import lombok.Getter;
import lombok.Setter;

@Table("transaction")
@Getter
@Setter
public class Transaction {
	
	@Id
	@Column("id")
	private UUID uuid;
	
	@Column("account_id")
	private String accountId;
	
	@Column("type")
	private String type;
	
	@Column("amount")
	private Double amount;
	
	@Column("currency")
	private String currency;
	
	@Column("description")
	private String description;
	
	@Column("status")
	private String status;
	
	@Column("provider_transaction_id")
	private String providerTransactionId;
	
	@Column("balance_after")
	private Double balanceAfter;
	
	@Column("created_at")
	private String createdAt; 
	
	@Column("code")
	private String code;
	
}

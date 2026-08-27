package com.transaction.spin.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionResponseDto {

	private String id;
	private String accountId;
	private String type;
	private Double amount;
	private String currency;
	private String description;
	private String status;
	private String providerTransactionId;
	private Double balanceAfter;
	private String icreatedAtd;
}

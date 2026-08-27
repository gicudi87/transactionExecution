package com.transaction.spin.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResProviderDto {

	private String transactionId;
	private String status;
	private Double balance;
	private String executedAt;
	private String code;
	private String message;
}

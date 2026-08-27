package com.transaction.spin.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDto {

	private String accountId;
	private String type;
	private Double amount;
	private String currency;
	private String description;
}

package com.transaction.test.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.transaction.spin.dtos.TransactionResProviderDto;

public class TransactionResProviderDtoTest {

	TransactionResProviderDto trans = new TransactionResProviderDto();
	
	private static final String TRANSACTION_ID = "HGTO-123";
	private static final String STATUS = "PASS";
	private static final Double BALANCE = 10.00;
	private static final String EXECUTED_AT = "EXECUT";
	private static final String CODE = "90";
	private static final String MESSAGE = "MESSAGE";
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		//Set
		trans.setTransactionId(TRANSACTION_ID);
		trans.setStatus(STATUS);
		trans.setBalance(BALANCE);
		trans.setExecutedAt(EXECUTED_AT);
		trans.setCode(CODE);
		trans.setMessage(MESSAGE);
	}
	
	@Test
	public void testTransDtoGet() {
		
		//Get
		assertEquals(TRANSACTION_ID, trans.getTransactionId());
		assertEquals(STATUS, trans.getStatus());
		assertEquals(BALANCE, trans.getBalance());
		assertEquals(EXECUTED_AT, trans.getExecutedAt());
		assertEquals(CODE, trans.getCode());
		assertEquals(MESSAGE, trans.getMessage());

	}
}

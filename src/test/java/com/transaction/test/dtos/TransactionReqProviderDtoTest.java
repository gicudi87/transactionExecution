package com.transaction.test.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.transaction.spin.dtos.TransactionReqProviderDto;

public class TransactionReqProviderDtoTest {

	private TransactionReqProviderDto trans = new TransactionReqProviderDto();
			
	private static final String ACCOUNT_ID = "ghu-1234";
	private static final String TYPE = "DEBIT";
	private static final Double AMOUNT = 10.00;
	private static final String CURRENCY = "MXN";
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		//Set
		trans.setAccountId(ACCOUNT_ID);
		trans.setType(TYPE);
		trans.setAmount(AMOUNT);
		trans.setCurrency(CURRENCY);
	}
	
	@Test
	public void testTransDtoGet() {
		
		//Get
		assertEquals(ACCOUNT_ID, trans.getAccountId());
		assertEquals(TYPE, trans.getType());
		assertEquals(AMOUNT, trans.getAmount());
		assertEquals(CURRENCY, trans.getCurrency());

	}
		
}

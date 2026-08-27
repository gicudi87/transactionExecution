package com.transaction.spin.service;

import org.springframework.stereotype.Service;

import com.transaction.spin.dtos.TransactionReqProviderDto;
import com.transaction.spin.dtos.TransactionRequestDto;
import com.transaction.spin.dtos.TransactionResProviderDto;
import com.transaction.spin.dtos.TransactionResponseDto;
import com.transaction.spin.entity.Transaction;
import com.transaction.spin.utils.WebClientPost;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TransactionService {
	
	private static final Double MIN_AMOUNT = 1.00;
	private static final Double MAX_AMOUNT = 10000.00;
	private static final String TYPE_DEBIT = "DEBIT";
	private static final String CURRENCY = "MXN";
	private static final String STATUS_APPROVED = "APPROVED";
	private static final String STATUS_REJECTED = "REJECTED";
	
	private final WebClientPost webClientPost;
	
	public TransactionService(WebClientPost webClientPost) {
		this.webClientPost = webClientPost;
	}

	public TransactionResponseDto processTransaccion(TransactionRequestDto tranRequestDto) {
		
		//Valida el monto minimo de la transaccio, no debe ser menor a $1.00
		if(tranRequestDto.getAmount() < MIN_AMOUNT) {
			throw new IllegalArgumentException("amount debe ser mayor que "+MIN_AMOUNT);
		}

		//Valida el monto maximo de tarjetas de debito, no debe exeder a $10,000.00
		if(tranRequestDto.getAmount() > MAX_AMOUNT && tranRequestDto.getType() == TYPE_DEBIT) {
			throw new IllegalArgumentException("el monto de tarjetas de debito debe ser menor o igual que "+MAX_AMOUNT);
		}
		
		//Valida el tipo de moneda, no debe ser diferente a MXN		
		if(!tranRequestDto.getCurrency().equals(CURRENCY)) {
			throw new IllegalArgumentException("solo se acepta el tipo de moneda "+CURRENCY);
		}
		
		try {
			
			TransactionResProviderDto response =  webClientPost.transactionConsult(buildTransProvider(tranRequestDto),TransactionResProviderDto.class);
			
			if(response.getStatus().equals(STATUS_APPROVED)) {
				
			}else if(response.getStatus().equals(STATUS_REJECTED)) {
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}
	
	//Metodo para armar el objeto que envia al servicio del provedor.
	private TransactionReqProviderDto buildTransProvider(TransactionRequestDto transRequestDto) {
		
		TransactionReqProviderDto transReq = new TransactionReqProviderDto();
		transReq.setAccountId(transRequestDto.getAccountId());
		transReq.setAmount(transRequestDto.getAmount());
		transReq.setCurrency(transRequestDto.getCurrency());
		transReq.setType(transRequestDto.getType());
		
		return transReq;
	}
	
	private Transaction buildObjectTrans(TransactionRequestDto tranRequestDto) {
		Transaction transaction = new Transaction();
		
		transaction.setAccountId(tranRequestDto.getAccountId());
		transaction.setType(tranRequestDto.getType());
		
		return null;
	}
}

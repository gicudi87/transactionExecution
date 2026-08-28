package com.transaction.spin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.spin.dtos.TransactionRequestDto;
import com.transaction.spin.dtos.TransactionResProviderDto;
import com.transaction.spin.entity.Transaction;
import com.transaction.spin.repository.TransactionRepository;
import com.transaction.spin.utils.Utils;
import com.transaction.spin.utils.WebClientPost;
import com.transaction.spin.utils.builders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionService {
	
	
	private final WebClientPost webClientPost;
	private final TransactionRepository transactionRepository;
	
	public TransactionService(WebClientPost webClientPost, TransactionRepository transactionRepository) {
		this.webClientPost = webClientPost;
		this.transactionRepository = transactionRepository;
	}

	/**
	 * Es el metodo que realiza las transacciones financieras y consulta el servicio del provedor
	 * 
	 * @param tranRequestDto Es el objeto del request que recibe
	 * @return Transaction   Devuelve el opnjeto de la transaccion que almacena
	 */
	@Transactional
	public Transaction processTransaccion(TransactionRequestDto tranRequestDto) {		
		Transaction transaction = new Transaction();
		
		//Valida el monto minimo de la transaccio, no debe ser menor a $1.00
		if(tranRequestDto.getAmount() < Utils.MIN_AMOUNT) {
			log.info("amount debe ser mayor que "+Utils.MIN_AMOUNT);
			transaction.setCode("amount debe ser mayor que "+Utils.MIN_AMOUNT);
			return transaction;
		}

		//Valida el monto maximo de tarjetas de debito, no debe exeder a $10,000.00
		if(tranRequestDto.getAmount() > Utils.MAX_AMOUNT && tranRequestDto.getType() == Utils.TYPE_DEBIT) {
			log.info("el monto de tarjetas de debito debe ser menor o igual que "+Utils.MAX_AMOUNT);
			transaction.setCode("el monto de tarjetas de debito debe ser menor o igual que "+Utils.MAX_AMOUNT);
			return transaction;
		}
		
		//Valida el tipo de moneda, no debe ser diferente a MXN		
		if(!tranRequestDto.getCurrency().equals(Utils.CURRENCY)) {
			log.info("solo se acepta el tipo de moneda "+Utils.CURRENCY);
			transaction.setCode("solo se acepta el tipo de moneda "+Utils.CURRENCY);
			return transaction;
		}
		
		//Guardamos la transaccion con estatus de proceso
		transaction = transactionRepository.save(builders.buildObjectTrans(tranRequestDto));
		
		try {
			
			//Se envia transaccion a provedor
			TransactionResProviderDto response =  webClientPost.transactionConsult(Utils.URL_PROVIDER,builders.buildTransProvider(tranRequestDto),TransactionResProviderDto.class);
			
			//Se valida el estatus de la transaccion recibida y segun el estatus se guarda informacion diferente
			if(response.getStatus().equals(Utils.STATUS_APPROVED)) {
				transaction.setStatus(Utils.STATUS_EXECUTED);
				transaction.setBalanceAfter(response.getBalance());
				transaction.setProviderTransactionId(response.getTransactionId());
				transaction.setDescription(Utils.MESSAGE_OK);
				transaction.setCreatedAt(response.getExecutedAt());		
				
			}else if(response.getStatus().equals(Utils.STATUS_REJECTED)) {
				transaction.setStatus(response.getStatus());
				transaction.setDescription(response.getMessage());
				transaction.setCode(response.getCode());
			}
			
			//Se guarda y retorna el objeto de la transaccion que se guardo
			return transactionRepository.saveAndFlush(transaction);
			
		} catch (Exception e) {		
			log.info("Failed transaction: "+e.getMessage());
			transaction.setStatus(Utils.STATUS_FAILED);
			transaction.setDescription(Utils.MESSAGE_FAILED);
			
			return transactionRepository.saveAndFlush(transaction);
		}
	}
	
	/**
	 * Metodo para consultar todas las transacciones
	 * 
	 * @return List<Transaction> Devuelve la lista de las transacciones consultadas
	 */
	public List<Transaction> getAllTransaction() {
		//Consulta en el repository el metodo por el Id
		return transactionRepository.findAll();
	}
	
}

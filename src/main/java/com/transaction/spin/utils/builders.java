package com.transaction.spin.utils;

import org.springframework.stereotype.Component;

import com.transaction.spin.dtos.TransactionReqProviderDto;
import com.transaction.spin.dtos.TransactionRequestDto;
import com.transaction.spin.entity.Transaction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class builders {

	//Metodo para armar el objeto que envia al servicio del provedor.
		public static TransactionReqProviderDto buildTransProvider(TransactionRequestDto transRequestDto) {
			
			TransactionReqProviderDto transReq = new TransactionReqProviderDto();
			transReq.setAccountId(transRequestDto.getAccountId());
			transReq.setAmount(transRequestDto.getAmount());
			transReq.setCurrency(transRequestDto.getCurrency());
			transReq.setType(transRequestDto.getType());
			
			return transReq;
		}
		
		//Metodo para construir el objeto inicial de la transaccion
		public static Transaction buildObjectTrans(TransactionRequestDto tranRequestDto) {
			Transaction transaction = new Transaction();
			
			transaction.setAccountId(tranRequestDto.getAccountId());
			transaction.setType(tranRequestDto.getType());
			transaction.setAmount(tranRequestDto.getAmount());
			transaction.setCurrency(tranRequestDto.getCurrency());
			transaction.setDescription(tranRequestDto.getDescription());
			transaction.setStatus(Utils.STATUS_PROCESS);
			
			return transaction;
		}
}

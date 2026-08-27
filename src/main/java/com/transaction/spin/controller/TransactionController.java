package com.transaction.spin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.spin.dtos.TransactionRequestDto;
import com.transaction.spin.dtos.TransactionResponseDto;
import com.transaction.spin.service.TransactionService;

@RestController
public class TransactionController {
	
	private final TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/transactions")
	public @ResponseBody ResponseEntity<TransactionResponseDto> transactionsRequest(@RequestBody TransactionRequestDto tranRequestDto) {
		//Consume el servicio para la consulta de transaccion
		TransactionResponseDto resp = transactionService.processTransaccion(tranRequestDto);
		
		//Valida el estatus de la respuesta y según el estatus devuelve conflicto o aceptada
		if(resp.getStatus() == "REJECTED") {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(resp);
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
	}
}

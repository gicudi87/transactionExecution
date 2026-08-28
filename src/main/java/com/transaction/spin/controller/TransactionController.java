package com.transaction.spin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.spin.dtos.TransactionRequestDto;
import com.transaction.spin.entity.Transaction;
import com.transaction.spin.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transacciones")
public class TransactionController {
	
	private final TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
     /**
      * Servicio para las transacciones financieras de las tarjetas de Credito y Debito
      * 
      * @param tranRequestDto
      * @return Transaction
      */
	@PostMapping
	public @ResponseBody ResponseEntity<Transaction> transactionsRequest(@RequestBody @Valid TransactionRequestDto tranRequestDto) {
		//Consume el servicio para la consulta de transaccion
		Transaction resp = transactionService.processTransaccion(tranRequestDto);
		
		//Valida el estatus de la respuesta y según el estatus devuelve conflicto o aceptada
		if(resp.getStatus() == "REJECTED") {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(resp);
		}
		//Cuando el estatus es  APPROVED devuelve estatus de ACCEPTED
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
	}
	

	/**
	 * Servicio para la consulta de todas las transacciones realizadas y que se han almacenado
	 * con sus diferentes estatus de aprobadas y rechazadas
	 * 
	 * @return List<Transaction> Devuelve una lista de todas las transacciones
	 */
	@GetMapping
	public @ResponseBody ResponseEntity<List<Transaction>> getTransaction(){
		//Consulta el servicio de una transaccion por ID
		List<Transaction> trans = transactionService.getAllTransaction();
		
		//Valida el resultado de la transaccion, si es null devuelve un estatus de conflicto
		if(trans == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(trans);
		}
		
		//Cuando no es null devuelve estatus de ACCEPTED
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(trans);
	}
}

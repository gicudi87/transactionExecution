package com.transaction.spin.controller;

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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
	public @ResponseBody Mono<ResponseEntity<Transaction>> transactionsRequest(@RequestBody @Valid TransactionRequestDto tranRequestDto) {
		//Consume el servicio para la consulta de transaccion
		return transactionService.processTransaccion(tranRequestDto)
				.map(resp -> {
					//Valida el estatus de la respuesta y según el estatus devuelve conflicto o aceptada
					if("REJECTED".equals(resp.getStatus())) {
						return ResponseEntity.status(HttpStatus.CONFLICT).body(resp);
					}
					//Cuando el estatus es  APPROVED devuelve estatus de ACCEPTED
					return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
				});
		
		
	}
	

	/**
	 * Servicio para la consulta de todas las transacciones realizadas y que se han almacenado
	 * con sus diferentes estatus de aprobadas y rechazadas
	 * 
	 * @return Flux<Transaction> Devuelve un flujo con todas las transacciones
	 */
	@GetMapping
	public @ResponseBody ResponseEntity<Flux<Transaction>> getTransaction(){
		//Consulta el servicio de todas las transacciones
		Flux<Transaction> trans = transactionService.getAllTransaction();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(trans);
	}
}

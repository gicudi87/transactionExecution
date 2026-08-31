package com.transaction.spin.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.transaction.spin.entity.Transaction;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, UUID>{
	

}

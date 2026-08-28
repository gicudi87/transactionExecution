package com.transaction.spin.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.spin.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID>{
	

}

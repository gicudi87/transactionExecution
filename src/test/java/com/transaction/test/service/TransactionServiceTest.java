package com.transaction.test.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transaction.spin.dtos.TransactionRequestDto;
import com.transaction.spin.entity.Transaction;
import com.transaction.spin.repository.TransactionRepository;
import com.transaction.spin.service.TransactionService;
import com.transaction.spin.utils.Utils;
import com.transaction.spin.utils.WebClientPost;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

	@Mock
    private WebClientPost webClientPost;
	
	@Mock
	private TransactionRepository transactionRepository;
	
	@InjectMocks
	private TransactionService transactionService;
	
	private TransactionRequestDto buildRequest(Double amount, String type, String currency) {
        TransactionRequestDto dto = new TransactionRequestDto();
        dto.setAmount(amount);
        dto.setType(type);
        dto.setCurrency(currency);
        return dto;
    }
	
	@Test
    void transaccionMontoMinimo() {
        TransactionRequestDto request = buildRequest(0.50, "CREDITO", Utils.CURRENCY);

        Transaction resultado = transactionService.processTransaccion(request);

        assertThat(resultado.getCode()).isEqualTo("amount debe ser mayor que " + Utils.MIN_AMOUNT);
        verifyNoInteractions(transactionRepository);
        verifyNoInteractions(webClientPost);
    }
	
	@Test
    void transaccionmontoMayorDebito() {
        TransactionRequestDto request = buildRequest(15000.0, Utils.TYPE_DEBIT, Utils.CURRENCY);

        Transaction resultado = transactionService.processTransaccion(request);

        assertThat(resultado.getCode())
                .isEqualTo("el monto de tarjetas de debito debe ser menor o igual que " + Utils.MAX_AMOUNT);
        verifyNoInteractions(transactionRepository);
        verifyNoInteractions(webClientPost);
    }

    @Test
    void transaccionmonedaMXN() {
        TransactionRequestDto request = buildRequest(100.0, "CREDITO", "USD");

        Transaction resultado = transactionService.processTransaccion(request);

        assertThat(resultado.getCode()).isEqualTo("solo se acepta el tipo de moneda " + Utils.CURRENCY);
        verifyNoInteractions(transactionRepository);
        verifyNoInteractions(webClientPost);
    }
	
		
	@Test
	void getAllListTransaction() {
		
		List<Transaction> transacciones = Arrays.asList(new Transaction(), new Transaction());
        when(transactionRepository.findAll()).thenReturn(transacciones);

        List<Transaction> resultado = transactionService.getAllTransaction();

        assertThat(resultado).hasSize(2);
        verify(transactionRepository, times(1)).findAll();
		
	}
	
	@Test
    void getAllTransactionEmpty() {
        when(transactionRepository.findAll()).thenReturn(List.of());

        List<Transaction> resultado = transactionService.getAllTransaction();

        assertThat(resultado).isEmpty();
    }
}

package com.transaction.spin.utils;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.transaction.spin.dtos.TransactionReqProviderDto;

@Component
public class WebClientPost {
	
	private static final String urlProvider = "/provider/v1/execute";

	private final WebClient webClient = WebClient.builder()
			.baseUrl(urlProvider)
			.defaultHeader("Content-Type", "application/json")
			.build();
	
	public <T> T transactionConsult(TransactionReqProviderDto tranProviderDto, Class<T> classOut){
		return new Gson().fromJson(
		     webClient.post()
				.body(BodyInserters.fromValue(tranProviderDto))
				.exchangeToMono(clientResponse -> {
					return clientResponse.bodyToMono(String.class);
				})
				.timeout(Duration.ofSeconds(30))
				.block(), classOut);
				
	}
}

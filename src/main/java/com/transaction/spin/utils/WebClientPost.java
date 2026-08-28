package com.transaction.spin.utils;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.transaction.spin.dtos.TransactionReqProviderDto;

@Component
public class WebClientPost {	
	
	
	public <T> T transactionConsult(String url, TransactionReqProviderDto tranProviderDto, Class<T> classOut){
		return new Gson().fromJson(
			WebClient.builder()
		        .baseUrl(url)
				.defaultHeader("Content-Type", "application/json")
				.build()
				.post()
				.body(BodyInserters.fromValue(tranProviderDto))
				.exchangeToMono(clientResponse -> {
					return clientResponse.bodyToMono(String.class);
				})
				.timeout(Duration.ofSeconds(30))
				.block(), classOut);
				
	}
}

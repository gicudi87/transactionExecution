package com.transaction.spin.utils;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.google.gson.Gson;
import com.transaction.spin.dtos.TransactionReqProviderDto;

import reactor.core.publisher.Mono;

@Component
public class WebClientPost {	
	
	private final WebClient webClient = WebClient.builder().build();
	private final Gson gson = new Gson();
	
	public <T> Mono<T> transactionConsult(String url, TransactionReqProviderDto tranProviderDto, Class<T> classOut) {
		return webClient.post()
				.uri(url)
				.header("Content-Type", "application/json")
				.bodyValue(tranProviderDto)
				.retrieve()
				.onStatus(status -> status.isError(), clientResponse ->
						clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(new WebClientResponseException(
										clientResponse.statusCode().value(),
										clientResponse.statusCode().toString(),
										clientResponse.headers().asHttpHeaders(),
										body.getBytes(),
										null)))
				)
				.bodyToMono(String.class)
				.timeout(Duration.ofSeconds(30))
				.map(body -> gson.fromJson(body, classOut));
	}
}

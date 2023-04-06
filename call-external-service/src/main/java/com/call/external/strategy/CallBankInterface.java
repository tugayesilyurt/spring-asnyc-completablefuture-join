package com.call.external.strategy;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.client.RestTemplate;

public interface CallBankInterface {

	CompletableFuture<String> execute(RestTemplate restTemplate);

}
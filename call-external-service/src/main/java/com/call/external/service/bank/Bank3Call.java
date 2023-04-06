package com.call.external.service.bank;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.call.external.strategy.CallBankInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Bank3Call implements CallBankInterface {

	@Override
	@Async("callAllBank")
	public CompletableFuture<String> execute(RestTemplate restTemplate) {

		log.info("Bank 3 : {}", Thread.currentThread().getName());
		String results = null;
		try {
			results = restTemplate.getForObject("http://localhost:5000/v1/bank/3", String.class);
		} catch (Exception e) {
			log.error("Bank 3 Error : " + e.getMessage());
			results = "Bank 3 Error";
		}
		return CompletableFuture.completedFuture(results);

	}

}

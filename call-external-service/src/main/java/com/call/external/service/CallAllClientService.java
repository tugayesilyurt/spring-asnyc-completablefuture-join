package com.call.external.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.call.external.strategy.ExternalCallBankStrategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CallAllClientService {

	private final ExternalCallBankStrategy externalCallBankStrategy;
	private final RestTemplate restTemplate;

	public List<String> getAllInfoFromBanks() throws InterruptedException, ExecutionException {

		long start = System.currentTimeMillis();

		List<String> weWillCallTheseBanks = Arrays.asList("BANK1", "BANK2", "BANK3");

		List<CompletableFuture<String>> response = weWillCallTheseBanks.stream().map(data -> {
			return externalCallBankStrategy.getBankService(data).execute(restTemplate);
		}).collect(Collectors.toList());

		CompletableFuture<List<String>> stringResponse = allOf(response);

		List<String> allData = stringResponse.get();

		log.info("Response time: " + (System.currentTimeMillis() - start));
		allData.stream().forEach(item -> {
			log.info("Response : " + item);
		});
		
		return allData;

	}

	public <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futuresList) {
		CompletableFuture<Void> allFuturesResult = CompletableFuture
				.allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));
		return allFuturesResult
				.thenApply(v -> futuresList.stream().map(future -> future.join()).collect(Collectors.<T>toList()));
	}

}
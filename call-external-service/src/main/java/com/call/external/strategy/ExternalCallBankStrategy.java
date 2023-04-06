package com.call.external.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.call.external.service.bank.Bank1Call;
import com.call.external.service.bank.Bank2Call;
import com.call.external.service.bank.Bank3Call;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ExternalCallBankStrategy {

	private final Map<String, CallBankInterface> listOfBank = new HashMap<String, CallBankInterface>();
	private final Bank1Call bank1Call;
	private final Bank2Call bank2Call;
	private final Bank3Call bank3Call;

	@PostConstruct
	void init() {
		listOfBank.put("BANK1", bank1Call);
		listOfBank.put("BANK2", bank2Call);
		listOfBank.put("BANK3", bank3Call);
	}

	public CallBankInterface getBankService(String prefix) {
		return listOfBank.get(prefix);
	}
}
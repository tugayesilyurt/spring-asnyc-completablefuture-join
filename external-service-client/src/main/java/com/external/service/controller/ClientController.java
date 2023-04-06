package com.external.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bank")
public class ClientController {
	
	@GetMapping(value = "/1")
	public String getBank1Info() throws InterruptedException {
		Thread.sleep(2000);
		return "Bank 1 is okey!";
	}
	
	@GetMapping(value = "/2")
	public String getBank2Info() throws InterruptedException {
		Thread.sleep(2000);
		return "Bank 2 is okey!";
	}
	
	@GetMapping(value = "/3")
	public String getBank3Info() throws InterruptedException {
		Thread.sleep(2000);
		return "Bank 3 is okey!";
	}

}

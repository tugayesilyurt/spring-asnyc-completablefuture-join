package com.call.external.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.call.external.service.CallAllClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/call")
@RequiredArgsConstructor
public class CallAllClientController {
	
	private final CallAllClientService callAllClientService;
	
	@GetMapping
	public ResponseEntity<?> getAllClientInformation() throws InterruptedException, ExecutionException{
		return new ResponseEntity<List<String>>(callAllClientService.getAllInfoFromBanks(),HttpStatus.OK);
	}

}

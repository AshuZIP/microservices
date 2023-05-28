package com.ashu.microservice.dev.currencyconvertermicroservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ashu.microservice.dev.currencyconvertermicroservice.entity.CurrencyConversion;
import com.ashu.microservice.dev.currencyconvertermicroservice.feignclientproxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping(value = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://currency-exchange:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		//ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		BigDecimal calculatedAmount = responseEntity.getBody().getconversionMultiplier().multiply(quantity);
		return new CurrencyConversion(responseEntity.getBody().getId(), from, to, quantity, responseEntity.getBody().getconversionMultiplier(), calculatedAmount, responseEntity.getBody().getEnvironment()+" RestTemplate.");
	}
	
	@GetMapping(value = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateTheCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversion retrievdObj = currencyExchangeProxy.retireveCurrencyExchangeValue(from, to);
		return new CurrencyConversion(retrievdObj.getId(), from, to, quantity, retrievdObj.getconversionMultiplier(), retrievdObj.getconversionMultiplier().multiply(quantity), retrievdObj.getEnvironment() + " Feign client.");
		
	}

}

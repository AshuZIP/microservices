/**
 * 
 */
package com.ashu.microservice.dev.currencyexchangemicroservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ashu.microservice.dev.currencyexchangemicroservice.entity.CurrencyExchange;
import com.ashu.microservice.dev.currencyexchangemicroservice.repository.CurrencyExchangeRepository;

/**
 * @author Ashutosh.Ranjan
 *
 */

@RestController
public class CurrencyExchangeController {
	
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;
	
	@Autowired
	private Environment environemnt;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retireveCurrencyExchangeValue(@PathVariable String from, @PathVariable String to) {
		double currencyMultiplier = currencyExchangeRepo.getCurrencyMultiplier(from, to);
		System.out.println(currencyMultiplier + " -> from my custom query!");
		System.out.println(currencyExchangeRepo.findByFromAndTo(from, to).getConversionMultiplier() + " -> from writing findByFromAndTo(String from, String to) custom query!");
		CurrencyExchange currencyExchange = new CurrencyExchange(1000l, from, to, BigDecimal.valueOf(currencyMultiplier));
		currencyExchange.setEnvironment(environemnt.getProperty("local.server.port"));
		return currencyExchange;
	}
}

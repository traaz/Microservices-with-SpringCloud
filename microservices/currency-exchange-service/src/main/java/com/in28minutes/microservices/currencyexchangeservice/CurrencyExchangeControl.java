package com.in28minutes.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeControl {
	@Autowired
	private CurrencyExchangeRepository repository;
	@Autowired
	private Environment environment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		//CurrencyExchange currencyExchange = new CurrencyExchange(100L, from, to, BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange=repository.findByFromAndTo(from, to);
		String port=environment.getProperty("local.server.port");
		if(currencyExchange == null) {
			throw new RuntimeException("Unabled find data");
		}
		
		currencyExchange.setEnvironment(port);
		
		return currencyExchange;
	}
}

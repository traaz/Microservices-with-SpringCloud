package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion  calculateCurrencyConversionFeign(@PathVariable String from, 
			@PathVariable String to, 
			@PathVariable BigDecimal quantity ) {
		CurrencyConversion currency = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversion(currency.getId(), from, to, quantity, 
				currency.getConversionMultiple(), 
				quantity.multiply(currency.getConversionMultiple()),
				currency.getEnvironment() );
	}
}

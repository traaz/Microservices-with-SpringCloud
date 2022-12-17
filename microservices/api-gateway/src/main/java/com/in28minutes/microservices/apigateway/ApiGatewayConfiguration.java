package com.in28minutes.microservices.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p ->p.path("/get")	
						.uri("http://httpbin.org:80"))
				.route(p->p.path("/currency-exchange/**") //serverdan bbu gelirse
				.uri("lb://currency-exchange"))	//yonlendirme burda	
				
				.route(p->p.path("/currency-conversion-feign/**") //serverdan bbu gelirse
						.uri("lb://currency-conversion"))
				//http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/EUR/to/INR/quantity/10
				//Yeni :=> http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
				.build();
	}
}

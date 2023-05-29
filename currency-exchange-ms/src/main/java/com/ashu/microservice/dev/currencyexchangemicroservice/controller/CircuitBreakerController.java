/**
 * 
 */
package com.ashu.microservice.dev.currencyexchangemicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * @author Ashutosh.Ranjan
 *
 */

@RestController
public class CircuitBreakerController {
	
	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping(value="/circuit-breaker")
	@Retry(name = "circuit-breaker", fallbackMethod = "fallbackMethodForCircuitBreaker")
	//@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethodForCircuitBreaker")
	//@RateLimiter(name="circuit-breaker")
	//@Bulkhead(name="circuit-breaker")
	public String result() {
		//logger.info("Circuit breaker with resilience4j");
		return "Helo from rate limiter";
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:9090/hello", String.class);
//		return forEntity.getBody();//"<h1>Hello From circuit breaker</h1>";
	}
	
	public String fallbackMethodForCircuitBreaker(Throwable th) {
		return th.getLocalizedMessage();
		//return "Unable to reach the service. Please come after some time..... Sorry for the inconvenience";
	}
	
}

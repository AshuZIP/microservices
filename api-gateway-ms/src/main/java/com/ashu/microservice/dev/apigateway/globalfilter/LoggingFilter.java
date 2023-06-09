/**
 * 
 */
package com.ashu.microservice.dev.apigateway.globalfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author Ashutosh.Ranjan
 *
 */

@Component
public class LoggingFilter implements GlobalFilter {
	
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		logger.info("Path logged is -> {}", exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}

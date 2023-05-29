/**
 * 
 */
package com.ashu.microservice.dev.currencyexchangemicroservice.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashu.microservice.dev.currencyexchangemicroservice.entity.CurrencyExchange;

/**
 * @author Ashutosh.Ranjan
 *
 */
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{
	
	
	CurrencyExchange findByFromAndTo(String from, String to);
	
	@Query(value = "select conversion_multiplier from currency_exchange where currency_from=:from and currency_to=:to", nativeQuery = true)
	public double getCurrencyMultiplier(@Param(value = "from") String from, @Param(value = "to") String to);

}

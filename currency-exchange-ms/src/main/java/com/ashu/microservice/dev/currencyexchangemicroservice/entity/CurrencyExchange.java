/**
 * 
 */
package com.ashu.microservice.dev.currencyexchangemicroservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ashutosh.Ranjan
 *
 */

@Entity
public class CurrencyExchange {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "currency_from")
	private String from;
	
	@Column(name = "currency_to")
	private String to;
	
	private BigDecimal conversionMultiplier;
	private String environment;

	public CurrencyExchange() {
		super();
	}

	public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiplier) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiplier = conversionMultiplier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionMultiplier() {
		return conversionMultiplier;
	}

	public void setConversionMultiplier(BigDecimal conversionMultiplier) {
		this.conversionMultiplier = conversionMultiplier;
	}
	
	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
}

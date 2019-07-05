package it.micheledichio.moneytransfers.model;

import java.math.BigDecimal;

public class Rate {
	
	private Long id;
	private String currency;
	private BigDecimal gbpValue;
	
	public Rate() {}

	public Rate(Long id, String currency, BigDecimal gbpValue) {
		this.id = id;
		this.currency = currency;
		this.gbpValue = gbpValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getGbpValue() {
		return gbpValue;
	}

	public void setGbpValue(BigDecimal gbpValue) {
		this.gbpValue = gbpValue;
	}

}

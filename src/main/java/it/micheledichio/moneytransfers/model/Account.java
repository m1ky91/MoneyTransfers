package it.micheledichio.moneytransfers.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class Account implements Validable{
	
	private String number;
	private BigDecimal balance;
	private String currency;
	private String sortCode;
	
	public Account() {}

	public Account(String number, BigDecimal balance, String currency, String sortCode) {
		this.number = number;
		this.balance = balance;
		this.currency = currency;
		this.sortCode = sortCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	@ApiModelProperty(hidden = true)
	@Override
	public boolean isValid() {
		return number != null && !number.isEmpty()
				&& balance != null && (balance.compareTo(BigDecimal.ZERO) >= 0)
				&& currency != null
				&& sortCode != null;
	}

}

package it.micheledichio.revolut.moneytransfers.model;

import java.math.BigDecimal;

public class Transfer {
	
	private Long id;
	private BigDecimal amount;
	private String reference;
	private String sender;
	private String beneficiary;
	
	public Transfer() {}

	public Transfer(Long id, BigDecimal amount, String reference, String sender, String beneficiary) {
		this.id = id;
		this.amount = amount;
		this.reference = reference;
		this.sender = sender;
		this.beneficiary = beneficiary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

}

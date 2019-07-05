package it.micheledichio.moneytransfers.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class Transfer implements Validable {
	
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

	@ApiModelProperty(hidden = true)
	@Override
	public boolean isValid() {
		return amount != null && (amount.compareTo(BigDecimal.ZERO) >= 0)
				&& reference != null && !reference.isEmpty()
				&& sender != null && !sender.isEmpty()
				&& beneficiary != null && !beneficiary.isEmpty();
	}

}

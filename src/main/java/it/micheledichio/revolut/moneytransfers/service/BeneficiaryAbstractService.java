package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Beneficiary;

public interface BeneficiaryAbstractService {
	
	public List<Beneficiary> getAll();

	public Beneficiary getByUsername(String username);

	public Beneficiary create(Beneficiary beneficiary);

	public Beneficiary update(Beneficiary beneficiary);

}

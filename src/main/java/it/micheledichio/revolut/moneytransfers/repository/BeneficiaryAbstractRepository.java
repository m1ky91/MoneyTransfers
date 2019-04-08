package it.micheledichio.revolut.moneytransfers.repository;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Beneficiary;

public interface BeneficiaryAbstractRepository {
	
	public List<Beneficiary> findAll();
	
	public Beneficiary findByUsername(String username);

	public Beneficiary save(Beneficiary beneficiary);

	public Beneficiary update(Beneficiary beneficiary);

}

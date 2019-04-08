package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Beneficiary;
import it.micheledichio.revolut.moneytransfers.repository.BeneficiaryAbstractRepository;
import it.micheledichio.revolut.moneytransfers.repository.Database;

public class BeneficiaryService implements BeneficiaryAbstractService {
	
	BeneficiaryAbstractRepository beneficiaryRepository;
	
	public BeneficiaryService() {
		beneficiaryRepository = Database.getInstance().getBeneficiaryRepository();
	}
	
	public BeneficiaryService(BeneficiaryAbstractRepository beneficiaryRepository) {
		this.beneficiaryRepository = beneficiaryRepository;
	}

	@Override
	public List<Beneficiary> getAll() {
		return beneficiaryRepository.findAll();
	}

	@Override
	public Beneficiary getByUsername(String username) {
		return beneficiaryRepository.findByUsername(username);
	}

	@Override
	public Beneficiary create(Beneficiary beneficiary) {
		return beneficiaryRepository.save(beneficiary);
	}

	@Override
	public Beneficiary update(Beneficiary beneficiary) {
		return beneficiaryRepository.update(beneficiary);
	}

}

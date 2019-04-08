package it.micheledichio.revolut.moneytransfers.repository;

import java.util.LinkedList;
import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Beneficiary;

public class BeneficiaryRepository implements BeneficiaryAbstractRepository {
	
	private List<Beneficiary> entities = new LinkedList<Beneficiary>();
	
	BeneficiaryRepository() {
		entities.add(new Beneficiary("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456"));
		entities.add(new Beneficiary("jsmith", "password2", "John", "Smith", "GB29NWBK60161331926819"));
	}

	@Override
	public List<Beneficiary> findAll() {
		return entities;
	}

	@Override
	public Beneficiary findByUsername(String username) {
		return entities.stream()
				.filter(e -> e.getUsername().equals(username))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Beneficiary save(Beneficiary benecifiary) {
		entities.add(benecifiary);
		return benecifiary;
	}

	@Override
	public Beneficiary update(Beneficiary beneficiary) {
		Beneficiary existingBeneficiary = entities.stream()
				.filter(e -> e.getUsername().equals(beneficiary.getUsername()))
				.findFirst()
				.orElse(null);
		
		if (existingBeneficiary != null) {
			existingBeneficiary.setPassword(beneficiary.getPassword());
			existingBeneficiary.setFirstName(beneficiary.getFirstName());
			existingBeneficiary.setLastName(beneficiary.getLastName());
			existingBeneficiary.setAccount(beneficiary.getAccount());
			existingBeneficiary.setMobilePhone(beneficiary.getMobilePhone());
			existingBeneficiary.setEmail(beneficiary.getEmail());
			
			return existingBeneficiary;
		} else {
			return null;
		}
	}

}

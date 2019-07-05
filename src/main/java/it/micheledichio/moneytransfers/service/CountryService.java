package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Country;
import it.micheledichio.moneytransfers.repository.CountryAbstractRepository;
import it.micheledichio.moneytransfers.repository.Database;

import java.util.List;

public class CountryService implements CountryAbstractService {
	
	CountryAbstractRepository countryRepository;
	
	public CountryService() {
		countryRepository = Database.getInstance().getCountryRepository();
	}
	
	public CountryService(CountryAbstractRepository countryRepository) {
		this.countryRepository = countryRepository; 
	}

	@Override
	public synchronized List<Country> getAll() {
		return countryRepository.findAll();
	}

	@Override
	public synchronized Country get(String id) {
		return countryRepository.findById(id);
	}

}

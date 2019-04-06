package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Country;
import it.micheledichio.revolut.moneytransfers.repository.CountryAbstractRepository;
import it.micheledichio.revolut.moneytransfers.repository.Database;

public class CountryService implements CountryAbstractService {
	
	CountryAbstractRepository countryRepository;
	
	public CountryService() {
		countryRepository = Database.getInstance().getCountryRepository();
	}
	
	public CountryService(CountryAbstractRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@Override
	public List<Country> getAll() {
		return countryRepository.findAll();
	}

	@Override
	public Country get(String id) {
		return countryRepository.findById(id);
	}

}

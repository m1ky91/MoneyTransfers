package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Country;

public interface CountryAbstractService {
	
	public List<Country> getAll();

	public Country get(String id);

}

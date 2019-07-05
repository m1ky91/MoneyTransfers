package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Country;

import java.util.List;

public interface CountryAbstractService {
	
	public List<Country> getAll();

	public Country get(String id);

}

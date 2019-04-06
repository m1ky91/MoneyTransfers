package it.micheledichio.revolut.moneytransfers.repository;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Country;

public interface CountryAbstractRepository {

	public List<Country> findAll();
	
	public Country findById(String id);
	
}

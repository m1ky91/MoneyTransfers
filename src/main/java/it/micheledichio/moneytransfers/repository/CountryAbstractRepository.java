package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.Country;

import java.util.List;

public interface CountryAbstractRepository {

	public List<Country> findAll();
	
	public Country findById(String id);
	
}

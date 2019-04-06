package it.micheledichio.revolut.moneytransfers.repository;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Rate;

public interface RateAbstractRepository {
	
	public List<Rate> findAll();
	
	public Rate findById(Long id);

}

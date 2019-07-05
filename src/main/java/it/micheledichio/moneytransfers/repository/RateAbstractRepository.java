package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.Rate;

import java.util.List;

public interface RateAbstractRepository {
	
	public List<Rate> findAll();
	
	public Rate findById(Long id);

}

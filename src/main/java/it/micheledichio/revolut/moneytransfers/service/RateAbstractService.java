package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Rate;

public interface RateAbstractService {
	
	public List<Rate> getAll();

	public Rate getById(Long id);

}

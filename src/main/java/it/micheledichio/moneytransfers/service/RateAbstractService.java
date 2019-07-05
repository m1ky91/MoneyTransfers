package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Rate;

import java.util.List;

public interface RateAbstractService {
	
	public List<Rate> getAll();

	public Rate getById(Long id);

}

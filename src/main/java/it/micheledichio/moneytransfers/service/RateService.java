package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Rate;
import it.micheledichio.moneytransfers.repository.Database;
import it.micheledichio.moneytransfers.repository.RateAbstractRepository;

import java.util.List;

public class RateService implements RateAbstractService {
	
	RateAbstractRepository rateRepository;
	
	public RateService() {
		rateRepository = Database.getInstance().getRateRepository();
	}
	
	public RateService(RateAbstractRepository rateRepository) {
		this.rateRepository = rateRepository;
	}

	@Override
	public synchronized List<Rate> getAll() {
		return rateRepository.findAll();
	}

	@Override
	public synchronized Rate getById(Long id) {
		return rateRepository.findById(id);
	}

} 

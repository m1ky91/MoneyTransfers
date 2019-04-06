package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Rate;
import it.micheledichio.revolut.moneytransfers.repository.Database;
import it.micheledichio.revolut.moneytransfers.repository.RateAbstractRepository;

public class RateService implements RateAbstractService {
	
	RateAbstractRepository rateRepository;
	
	public RateService() {
		rateRepository = Database.getInstance().getRateRepository();
	}
	
	public RateService(RateAbstractRepository rateRepository) {
		this.rateRepository = rateRepository;
	}

	@Override
	public List<Rate> getAll() {
		return rateRepository.findAll();
	}

	@Override
	public Rate getById(Long id) {
		return rateRepository.findById(id);
	}

}

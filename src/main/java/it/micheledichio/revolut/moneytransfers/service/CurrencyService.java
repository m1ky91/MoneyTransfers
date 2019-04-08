package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Currency;
import it.micheledichio.revolut.moneytransfers.repository.CurrencyAbstractRepository;
import it.micheledichio.revolut.moneytransfers.repository.Database;

public class CurrencyService implements CurrencyAbstractService {
	
	CurrencyAbstractRepository currencyRepository;
	
	public CurrencyService() {
		currencyRepository = Database.getInstance().getCurrencyRepository();
	}
	
	public CurrencyService(CurrencyAbstractRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public List<Currency> getAll() {
		return currencyRepository.findAll();
	}

	@Override
	public List<Currency> getByCode(String code) {
		return currencyRepository.findByCode(code);
	} 

}

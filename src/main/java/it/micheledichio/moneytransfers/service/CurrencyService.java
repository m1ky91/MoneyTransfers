package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Currency;
import it.micheledichio.moneytransfers.repository.CurrencyAbstractRepository;
import it.micheledichio.moneytransfers.repository.Database;

import java.util.List;

public class CurrencyService implements CurrencyAbstractService {
	
	CurrencyAbstractRepository currencyRepository;
	
	public CurrencyService() {
		currencyRepository = Database.getInstance().getCurrencyRepository();
	}
	
	public CurrencyService(CurrencyAbstractRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public synchronized List<Currency> getAll() {
		return currencyRepository.findAll();
	}

	@Override
	public synchronized List<Currency> getByCode(String code) {
		return currencyRepository.findByCode(code);
	} 

}

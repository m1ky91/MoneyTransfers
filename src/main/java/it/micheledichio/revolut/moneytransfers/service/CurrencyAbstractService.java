package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Currency;

public interface CurrencyAbstractService {
	
	public List<Currency> getAll();

	public List<Currency> getByCode(String code);

}

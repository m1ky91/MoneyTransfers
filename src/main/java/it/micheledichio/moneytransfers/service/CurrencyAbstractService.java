package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Currency;

import java.util.List;

public interface CurrencyAbstractService {
	
	public List<Currency> getAll();

	public List<Currency> getByCode(String code);

}

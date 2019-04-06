package it.micheledichio.revolut.moneytransfers.repository;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Currency;

public interface CurrencyAbstractRepository {
	
	public List<Currency> findAll();
	
	public List<Currency> findByCode (String code);

}

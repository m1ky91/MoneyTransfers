package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.Currency;

import java.util.List;

public interface CurrencyAbstractRepository {
	
	public List<Currency> findAll();
	
	public List<Currency> findByCode (String code);

}

package it.micheledichio.revolut.moneytransfers.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.micheledichio.revolut.moneytransfers.model.Currency;

public class CurrencyRepository implements CurrencyAbstractRepository {
	
	private List<Currency> entities = new LinkedList<Currency>();
	
	CurrencyRepository() {
		entities.add(new Currency("USD", "U.S. Dollar", "USA"));
		entities.add(new Currency("EUR", "European Euro","ITA"));
		entities.add(new Currency("JPY", "Japanese Yen", "JPN"));
		entities.add(new Currency("GBP", "British Pound", "GBR"));
	}

	@Override
	public List<Currency> findAll() {
		return entities;
	}

	@Override
	public List<Currency> findByCode(String code) {
		return entities.stream()
				.filter(e -> e.getCode().equals(code))
				.collect(Collectors.toList());
	}

}

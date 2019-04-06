package it.micheledichio.revolut.moneytransfers.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.micheledichio.revolut.moneytransfers.model.Country;
import it.micheledichio.revolut.moneytransfers.model.Currency;

public class CurrencyRepository implements CurrencyAbstractRepository {
	
	private List<Currency> entities = new LinkedList<Currency>();
	
	CurrencyRepository() {
		entities.add(new Currency("USD", "U.S. Dollar", new Country("USA", "United States")));
		entities.add(new Currency("EUR", "European Euro", new Country("ITA", "Italy")));
		entities.add(new Currency("JPY", "Japanese Yen", new Country("JPN", "Japan")));
		entities.add(new Currency("GBP", "British Pound", new Country("GBR", "United Kingdom")));
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

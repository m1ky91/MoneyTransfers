package it.micheledichio.revolut.moneytransfers.repository;

import java.util.LinkedList;
import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Country;

public class CountryRepository implements CountryAbstractRepository {

	private List<Country> entities = new LinkedList<Country>();
	
	CountryRepository() {
		entities.add(new Country("USA", "United States"));
		entities.add(new Country("ITA", "Italy"));
		entities.add(new Country("JPN", "Japan"));
		entities.add(new Country("USD", "United Kingdom"));
	}
	
	public List<Country> findAll() {
		return this.entities;
	}

	public Country findById(String id) {
		return entities.stream()
				.filter(e -> e.getCode().equals(id))
				.findFirst()
				.orElse(null);
	}
	
}

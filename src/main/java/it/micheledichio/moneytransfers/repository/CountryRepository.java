package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.Country;

import java.util.LinkedList;
import java.util.List;

public class CountryRepository implements CountryAbstractRepository {

	private List<Country> entities = new LinkedList<Country>();
	
	CountryRepository() {
		entities.add(new Country("USA", "United States"));
		entities.add(new Country("ITA", "Italy"));
		entities.add(new Country("JPN", "Japan"));
		entities.add(new Country("GBR", "United Kingdom"));
	}
	
	public List<Country> findAll() {
		return entities;
	}

	public Country findById(String id) {
		return entities.stream()
				.filter(e -> e.getCode().equals(id))
				.findFirst()
				.orElse(null);
	}
	
}

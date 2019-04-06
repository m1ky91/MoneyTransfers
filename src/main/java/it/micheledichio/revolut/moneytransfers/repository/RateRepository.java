package it.micheledichio.revolut.moneytransfers.repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import it.micheledichio.revolut.moneytransfers.model.Country;
import it.micheledichio.revolut.moneytransfers.model.Currency;
import it.micheledichio.revolut.moneytransfers.model.Rate;

public class RateRepository implements RateAbstractRepository {
	
	private AtomicLong count = new AtomicLong(0);
	private List<Rate> entities = new LinkedList<Rate>();
	
	RateRepository() {
		entities.add(new Rate(count.incrementAndGet(), new Currency("USD", "U.S. Dollar", new Country("USA", "United States")), new BigDecimal("0.76712")));
		entities.add(new Rate(count.incrementAndGet(), new Currency("EUR", "European Euro", new Country("ITA", "Italy")), new BigDecimal("0.86072")));
		entities.add(new Rate(count.incrementAndGet(), new Currency("JPY", "Japanese Yen", new Country("JPN", "Japan")), new BigDecimal("0.00686")));
		entities.add(new Rate(count.incrementAndGet(), new Currency("GBP", "British Pound", new Country("GBR", "United Kingdom")), new BigDecimal("1.00000")));
	}

	@Override
	public List<Rate> findAll() {
		return entities;
	}

	@Override
	public Rate findById(Long id) {
		return entities.stream()
				.filter(e -> e.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

}

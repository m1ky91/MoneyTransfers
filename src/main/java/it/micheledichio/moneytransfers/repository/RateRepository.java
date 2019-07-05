package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.Rate;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RateRepository implements RateAbstractRepository {
	
	private AtomicLong count = new AtomicLong(0);
	private List<Rate> entities = new LinkedList<Rate>();
	
	RateRepository() {
		entities.add(new Rate(count.incrementAndGet(), "USD", new BigDecimal("0.76712")));
		entities.add(new Rate(count.incrementAndGet(), "EUR", new BigDecimal("0.86072")));
		entities.add(new Rate(count.incrementAndGet(), "JPY", new BigDecimal("0.00686")));
		entities.add(new Rate(count.incrementAndGet(), "GBP", new BigDecimal("1.00000")));
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

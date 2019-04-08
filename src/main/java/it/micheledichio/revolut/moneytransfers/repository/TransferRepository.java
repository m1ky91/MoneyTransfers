package it.micheledichio.revolut.moneytransfers.repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import it.micheledichio.revolut.moneytransfers.model.Transfer;

public class TransferRepository implements TransferAbstractRepository {
	
	private AtomicLong count = new AtomicLong(0);
	private List<Transfer> entities = new LinkedList<Transfer>();
	
	TransferRepository() {
		entities.add(new Transfer(count.incrementAndGet(), new BigDecimal("80"), "invio 1", "mrossi", "jsmith"));
		entities.add(new Transfer(count.incrementAndGet(), new BigDecimal("90"), "invio 2", "jsmith", "mrossi"));
	}

	@Override
	public List<Transfer> findAll() {
		return entities;
	}

	@Override
	public Transfer findById(Long id) {
		return entities.stream()
				.filter(e -> e.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public List<Transfer> findBySender(String username) {
		return entities.stream()
				.filter(e -> e.getSender().equals(username))
				.collect(Collectors.toList());
	}

	@Override
	public Transfer save(Transfer transfer) {
		Transfer newTransfer = new Transfer(count.incrementAndGet(), transfer.getAmount(), transfer.getReference(), transfer.getSender(), transfer.getBeneficiary());
		entities.add(newTransfer);
		return newTransfer;
	}

}

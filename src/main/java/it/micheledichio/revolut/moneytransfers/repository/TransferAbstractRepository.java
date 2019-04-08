package it.micheledichio.revolut.moneytransfers.repository;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Transfer;

public interface TransferAbstractRepository {
	
	public List<Transfer> findAll();
	
	public Transfer findById(Long id);
	
	public List<Transfer> findBySender(String username);

	public Transfer save(Transfer transfer);

}

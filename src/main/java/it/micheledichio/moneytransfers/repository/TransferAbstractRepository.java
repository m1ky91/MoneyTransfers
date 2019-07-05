package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.Transfer;

import java.util.List;

public interface TransferAbstractRepository {
	
	public List<Transfer> findAll();
	
	public Transfer findById(Long id);
	
	public List<Transfer> findBySender(String username);

	public Transfer save(Transfer transfer);

}

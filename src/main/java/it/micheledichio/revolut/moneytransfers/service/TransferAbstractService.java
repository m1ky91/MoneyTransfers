package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Transfer;

public interface TransferAbstractService {
	
	public List<Transfer> getAll();

	public Transfer getById(Long id);
	
	public List<Transfer> getBySender(String username);

	public Transfer create(Transfer transfer);

}

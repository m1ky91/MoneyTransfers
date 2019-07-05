package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Transfer;

import java.util.List;

public interface TransferAbstractService {
	
	public List<Transfer> getAll();

	public Transfer getById(Long id);
	
	public List<Transfer> getBySender(String username);

	public Transfer create(Transfer transfer);

}

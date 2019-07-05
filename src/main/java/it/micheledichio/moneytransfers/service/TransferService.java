package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Transfer;
import it.micheledichio.moneytransfers.repository.Database;
import it.micheledichio.moneytransfers.repository.TransferAbstractRepository;

import java.util.List;

public class TransferService implements TransferAbstractService {
	
	TransferAbstractRepository transferRepository;
	
	public TransferService() {
		transferRepository = Database.getInstance().getTransferRepository();
	}
	
	public TransferService(TransferAbstractRepository transferRepository) {
		this.transferRepository = transferRepository;
	}

	@Override
	public synchronized List<Transfer> getAll() {
		return transferRepository.findAll();
	}

	@Override
	public synchronized Transfer getById(Long id) {
		return transferRepository.findById(id);
	}
	
	@Override
	public synchronized List<Transfer> getBySender(String username) {
		return transferRepository.findBySender(username);
	}

	@Override
	public synchronized Transfer create(Transfer transfer) {
		return transferRepository.save(transfer);
	} 

}

package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Transfer;
import it.micheledichio.revolut.moneytransfers.repository.Database;
import it.micheledichio.revolut.moneytransfers.repository.TransferAbstractRepository;

public class TransferService implements TransferAbstractService {
	
	TransferAbstractRepository transferRepository;
	
	public TransferService() {
		transferRepository = Database.getInstance().getTransferRepository();
	}
	
	public TransferService(TransferAbstractRepository transferRepository) {
		this.transferRepository = transferRepository;
	}

	@Override
	public List<Transfer> getAll() {
		return transferRepository.findAll();
	}

	@Override
	public Transfer getById(Long id) {
		return transferRepository.findById(id);
	}
	
	@Override
	public List<Transfer> getBySender(String username) {
		return transferRepository.findBySender(username);
	}

	@Override
	public Transfer create(Transfer transfer) {
		return transferRepository.save(transfer);
	} 

}

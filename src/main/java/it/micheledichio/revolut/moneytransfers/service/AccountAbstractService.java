package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Account;

public interface AccountAbstractService {
	
	public List<Account> getAll();

	public Account getByNumber(String number);

	public Account create(Account account);

	public Account update(Account account);

}

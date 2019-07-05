package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Account;

import java.util.List;

public interface AccountAbstractService {
	
	public List<Account> getAll();

	public Account getByNumber(String number);

	public Account create(Account account);

	public Account update(Account account);

}

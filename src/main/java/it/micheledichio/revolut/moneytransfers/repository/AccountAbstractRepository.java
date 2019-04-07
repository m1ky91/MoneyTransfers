package it.micheledichio.revolut.moneytransfers.repository;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.Account;

public interface AccountAbstractRepository {
	
	public List<Account> findAll();
	
	public Account findByNumber(String number);

	public Account save(Account account);

	public Account update(Account account);

}

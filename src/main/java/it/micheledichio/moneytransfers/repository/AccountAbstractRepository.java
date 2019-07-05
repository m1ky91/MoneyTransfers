package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.Account;

import java.util.List;

public interface AccountAbstractRepository {
	
	public List<Account> findAll();
	
	public Account findByNumber(String number);

	public Account save(Account account);

	public Account update(Account account);

}

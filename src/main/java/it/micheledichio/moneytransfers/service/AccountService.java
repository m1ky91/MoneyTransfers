package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.Account;
import it.micheledichio.moneytransfers.repository.AccountAbstractRepository;
import it.micheledichio.moneytransfers.repository.Database;

import java.util.List;

public class AccountService implements AccountAbstractService {
	
	AccountAbstractRepository accountRepository;
	
	public AccountService() {
		accountRepository = Database.getInstance().getAccountRepository();
	}
	
	public AccountService(AccountAbstractRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public synchronized List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public synchronized Account getByNumber(String number) {
		return accountRepository.findByNumber(number);
	}

	@Override
	public synchronized Account create(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public synchronized Account update(Account account) {
		return accountRepository.update(account);
	}

}

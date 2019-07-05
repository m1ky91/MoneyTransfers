package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.Account;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class AccountRepository implements AccountAbstractRepository {
	
	private List<Account> entities = new LinkedList<Account>();
	
	AccountRepository() {
		entities.add(new Account("IT60X0542811101000000123456", new BigDecimal("2500"), "EUR", null));
		entities.add(new Account("GB29NWBK60161331926819", new BigDecimal("3000"), "GBP", "123456"));
	}

	@Override
	public List<Account> findAll() {
		return entities;
	}

	@Override
	public Account findByNumber(String number) {
		return entities.stream()
				.filter(e -> e.getNumber().equals(number))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Account save(Account account) {
		entities.add(account);
		return account;
	}

	@Override
	public Account update(Account account) {
		Account existingAccount = entities.stream()
				.filter(e -> e.getNumber().equals(account.getNumber()))
				.findFirst()
				.orElse(null);
		
		if (existingAccount != null) {
			existingAccount.setBalance(account.getBalance());
			existingAccount.setCurrency(account.getCurrency());
			existingAccount.setSortCode(account.getSortCode());
			
			return existingAccount;
		} else {
			return null;
		}
	}

}

package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.User;

public interface UserAbstractService {
	
	public List<User> getAll();

	public User getByUsername(String username);

	public User create(User user);

	public User update(User user);

}

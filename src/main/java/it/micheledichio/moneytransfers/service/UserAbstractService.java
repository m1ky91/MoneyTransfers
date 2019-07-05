package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.User;

import java.util.List;

public interface UserAbstractService {
	
	public List<User> getAll();

	public User getByUsername(String username);

	public User create(User user);

	public User update(User user);

}

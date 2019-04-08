package it.micheledichio.revolut.moneytransfers.repository;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.User;

public interface UserAbstractRepository {
	
	public List<User> findAll();
	
	public User findByUsername(String username);

	public User save(User user);

	public User update(User user);

}

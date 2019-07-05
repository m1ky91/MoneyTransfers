package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.User;

import java.util.List;

public interface UserAbstractRepository {
	
	public List<User> findAll();
	
	public User findByUsername(String username);

	public User save(User user);

	public User update(User user);

}

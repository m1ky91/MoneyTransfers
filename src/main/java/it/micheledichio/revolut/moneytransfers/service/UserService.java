package it.micheledichio.revolut.moneytransfers.service;

import java.util.List;

import it.micheledichio.revolut.moneytransfers.model.User;
import it.micheledichio.revolut.moneytransfers.repository.UserAbstractRepository;
import it.micheledichio.revolut.moneytransfers.repository.Database;

public class UserService implements UserAbstractService {
	
	UserAbstractRepository userRepository;
	
	public UserService() {
		userRepository = Database.getInstance().getUserRepository();
	}
	
	public UserService(UserAbstractRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.update(user);
	} 

}

package it.micheledichio.moneytransfers.service;

import it.micheledichio.moneytransfers.model.User;
import it.micheledichio.moneytransfers.repository.Database;
import it.micheledichio.moneytransfers.repository.UserAbstractRepository;

import java.util.List;

public class UserService implements UserAbstractService {
	
	UserAbstractRepository userRepository;
	
	public UserService() {
		userRepository = Database.getInstance().getUserRepository();
	}
	
	public UserService(UserAbstractRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public synchronized List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public synchronized User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public synchronized User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public synchronized User update(User user) {
		return userRepository.update(user);
	} 

}

package it.micheledichio.moneytransfers.repository;

import it.micheledichio.moneytransfers.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserRepository implements UserAbstractRepository {
	
	private List<User> entities = new LinkedList<User>();
	
	UserRepository() {
		entities.add(new User("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456"));
		entities.add(new User("jsmith", "password2", "John", "Smith", "GB29NWBK60161331926819"));
	}

	@Override
	public List<User> findAll() {
		return entities;
	}

	@Override
	public User findByUsername(String username) {
		return entities.stream()
				.filter(e -> e.getUsername().equals(username))
				.findFirst()
				.orElse(null);
	}

	@Override
	public User save(User user) {
		entities.add(user);
		return user;
	}

	@Override
	public User update(User user) {
		User existingUser = entities.stream()
				.filter(e -> e.getUsername().equals(user.getUsername()))
				.findFirst()
				.orElse(null);
		
		if (existingUser != null) {
			existingUser.setPassword(user.getPassword());
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setAccount(user.getAccount());
			existingUser.setMobilePhone(user.getMobilePhone());
			existingUser.setEmail(user.getEmail());
			
			return existingUser;
		} else {
			return null;
		}
	}

}

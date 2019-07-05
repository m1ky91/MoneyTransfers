package it.micheledichio.moneytransfers.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.moneytransfers.model.User;
import it.micheledichio.moneytransfers.repository.UserAbstractRepository;
import it.micheledichio.moneytransfers.service.UserAbstractService;
import it.micheledichio.moneytransfers.service.UserService;

public class UserServiceTest {
	
	@Test
	public void getAllTest() {
		UserAbstractRepository repository = EasyMock.createMock(UserAbstractRepository.class);
        expect(repository.findAll()).andReturn(null);
        replay(repository);

        UserService service = new UserService(repository);
        assertEquals(null, service.getAll());

        verify(repository);
	}
	
	@Test
	public void getByUsernameTest() {
		UserAbstractRepository repository = EasyMock.createMock(UserAbstractRepository.class);
        expect(repository.findByUsername(null)).andReturn(null);
        replay(repository);

        UserService service = new UserService(repository);
        assertEquals(null, service.getByUsername(null));

        verify(repository);
	}
	
	@Test
	public void createTest() {
		UserAbstractRepository repository = EasyMock.createMock(UserAbstractRepository.class);
        expect(repository.save(null)).andReturn(null);
        replay(repository);

        UserService service = new UserService(repository);
        assertEquals(null, service.create(null));

        verify(repository);
	}
	
	@Test
	public void updateTest() {
		UserAbstractRepository repository = EasyMock.createMock(UserAbstractRepository.class);
        expect(repository.update(null)).andReturn(null);
        replay(repository);

        UserService service = new UserService(repository);
        assertEquals(null, service.update(null));

        verify(repository);
	}
	
	@Test
	public void givenMultiThread() {
		User newUser = new User();
        newUser.setUsername("ab");
        newUser.setPassword("password");
        newUser.setFirstName("a");
        newUser.setLastName("b");

		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		UserAbstractService service = new UserService();
		
		int preRaceConditionSize = service.getAll().size();
		
		IntStream.range(0, 10000).forEach(count -> threadPool.submit(() -> service.create(newUser)));
		try {
			threadPool.awaitTermination(50, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(preRaceConditionSize + 10000, service.getAll().size());
	}

}

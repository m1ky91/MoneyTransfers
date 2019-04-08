package it.micheledichio.revolut.moneytransfers.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.revolut.moneytransfers.repository.UserAbstractRepository;
import it.micheledichio.revolut.moneytransfers.service.UserService;

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

}

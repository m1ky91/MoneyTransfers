package it.micheledichio.revolut.moneytransfers.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.revolut.moneytransfers.repository.AccountAbstractRepository;
import it.micheledichio.revolut.moneytransfers.service.AccountService;

public class AccountServiceTest {
	
	@Test
	public void getAllTest() {
		AccountAbstractRepository repository = EasyMock.createMock(AccountAbstractRepository.class);
        expect(repository.findAll()).andReturn(null);
        replay(repository);

        AccountService service = new AccountService(repository);
        assertEquals(null, service.getAll());

        verify(repository);
	}
	
	@Test
	public void getByNumberTest() {
		AccountAbstractRepository repository = EasyMock.createMock(AccountAbstractRepository.class);
        expect(repository.findByNumber(null)).andReturn(null);
        replay(repository);

        AccountService service = new AccountService(repository);
        assertEquals(null, service.getByNumber(null));

        verify(repository);
	}
	
	@Test
	public void createTest() {
		AccountAbstractRepository repository = EasyMock.createMock(AccountAbstractRepository.class);
        expect(repository.save(null)).andReturn(null);
        replay(repository);

        AccountService service = new AccountService(repository);
        assertEquals(null, service.create(null));

        verify(repository);
	}
	
	@Test
	public void updateTest() {
		AccountAbstractRepository repository = EasyMock.createMock(AccountAbstractRepository.class);
        expect(repository.update(null)).andReturn(null);
        replay(repository);

        AccountService service = new AccountService(repository);
        assertEquals(null, service.update(null));

        verify(repository);
	}

}
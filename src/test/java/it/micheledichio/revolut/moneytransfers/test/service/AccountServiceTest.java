package it.micheledichio.revolut.moneytransfers.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.revolut.moneytransfers.model.Account;
import it.micheledichio.revolut.moneytransfers.repository.AccountAbstractRepository;
import it.micheledichio.revolut.moneytransfers.service.AccountAbstractService;
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
	
	@Test
	public void givenMultiThread() {
		Account newAccount = new Account();
		newAccount.setNumber("123456789");
		newAccount.setCurrency("EUR");
		newAccount.setBalance(new BigDecimal("1500"));
		newAccount.setSortCode("123456");

		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		AccountAbstractService service = new AccountService();
		
		int preRaceConditionSize = service.getAll().size();
		
		IntStream.range(0, 10000).forEach(count -> threadPool.submit(() -> service.create(newAccount)));
		try {
			threadPool.awaitTermination(50, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(preRaceConditionSize + 10000, service.getAll().size());
	}

}

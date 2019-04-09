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

import it.micheledichio.revolut.moneytransfers.model.Transfer;
import it.micheledichio.revolut.moneytransfers.repository.TransferAbstractRepository;
import it.micheledichio.revolut.moneytransfers.service.TransferAbstractService;
import it.micheledichio.revolut.moneytransfers.service.TransferService;

public class TransferServiceTest {
	
	@Test
	public void getAllTest() {
		TransferAbstractRepository repository = EasyMock.createMock(TransferAbstractRepository.class);
        expect(repository.findAll()).andReturn(null);
        replay(repository);

        TransferService service = new TransferService(repository);
        assertEquals(null, service.getAll());

        verify(repository);
	}
	
	@Test
	public void getByIdTest() {
		TransferAbstractRepository repository = EasyMock.createMock(TransferAbstractRepository.class);
        expect(repository.findById(null)).andReturn(null);
        replay(repository);

        TransferService service = new TransferService(repository);
        assertEquals(null, service.getById(null));

        verify(repository);
	}
	
	@Test
	public void getBySenderTest() {
		TransferAbstractRepository repository = EasyMock.createMock(TransferAbstractRepository.class);
        expect(repository.findBySender(null)).andReturn(null);
        replay(repository);

        TransferService service = new TransferService(repository);
        assertEquals(null, service.getBySender(null));

        verify(repository);
	}
	
	@Test
	public void createTest() {
		TransferAbstractRepository repository = EasyMock.createMock(TransferAbstractRepository.class);
        expect(repository.save(null)).andReturn(null);
        replay(repository);

        TransferService service = new TransferService(repository);
        assertEquals(null, service.create(null));

        verify(repository);
	}
	
	@Test
	public void givenMultiThread() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setReference("send 1");
		newTransfer.setSender("ab");
		newTransfer.setBeneficiary("cd");

		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		TransferAbstractService service = new TransferService();
		
		int preRaceConditionSize = service.getAll().size();
		
		IntStream.range(0, 10000).forEach(count -> threadPool.submit(() -> service.create(newTransfer)));
		try {
			threadPool.awaitTermination(50, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(preRaceConditionSize + 10000, service.getAll().size());
	}

}

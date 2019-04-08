package it.micheledichio.revolut.moneytransfers.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.revolut.moneytransfers.repository.TransferAbstractRepository;
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

}

package it.micheledichio.moneytransfers.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.moneytransfers.repository.RateAbstractRepository;
import it.micheledichio.moneytransfers.service.RateService;

public class RateServiceTest {
	
	@Test
	public void getAllTest() {
		RateAbstractRepository repository = EasyMock.createMock(RateAbstractRepository.class);
        expect(repository.findAll()).andReturn(null);
        replay(repository);

        RateService service = new RateService(repository);
        assertEquals(null, service.getAll());

        verify(repository);
	}
	
	@Test
	public void getByIdTest() {
		RateAbstractRepository repository = EasyMock.createMock(RateAbstractRepository.class);
        expect(repository.findById(null)).andReturn(null);
        replay(repository);

        RateService service = new RateService(repository);
        assertEquals(null, service.getById(null));

        verify(repository);
	}

}

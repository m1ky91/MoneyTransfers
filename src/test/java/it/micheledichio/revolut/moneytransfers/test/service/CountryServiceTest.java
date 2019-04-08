package it.micheledichio.revolut.moneytransfers.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.revolut.moneytransfers.repository.CountryAbstractRepository;
import it.micheledichio.revolut.moneytransfers.service.CountryService;

public class CountryServiceTest {
	
	@Test
	public void getAllTest() {
		CountryAbstractRepository repository = EasyMock.createMock(CountryAbstractRepository.class);
        expect(repository.findAll()).andReturn(null);
        replay(repository);

        CountryService service = new CountryService(repository);
        assertEquals(null, service.getAll());

        verify(repository);
	}
	
	@Test
	public void getTest() {
		CountryAbstractRepository repository = EasyMock.createMock(CountryAbstractRepository.class);
        expect(repository.findById(null)).andReturn(null);
        replay(repository);

        CountryService service = new CountryService(repository);
        assertEquals(null, service.get(null));

        verify(repository);
	}

}

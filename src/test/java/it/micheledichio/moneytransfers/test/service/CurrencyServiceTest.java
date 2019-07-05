package it.micheledichio.moneytransfers.test.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.moneytransfers.repository.CurrencyAbstractRepository;
import it.micheledichio.moneytransfers.service.CurrencyService;

public class CurrencyServiceTest {
	
	@Test
	public void getAllTest() {
		CurrencyAbstractRepository repository = EasyMock.createMock(CurrencyAbstractRepository.class);
        expect(repository.findAll()).andReturn(null);
        replay(repository);

        CurrencyService service = new CurrencyService(repository);
        assertEquals(null, service.getAll());

        verify(repository);
	}
	
	@Test
	public void getByCodeTest() {
		CurrencyAbstractRepository repository = EasyMock.createMock(CurrencyAbstractRepository.class);
        expect(repository.findByCode(null)).andReturn(null);
        replay(repository);

        CurrencyService service = new CurrencyService(repository);
        assertEquals(null, service.getByCode(null));

        verify(repository);
	}

}

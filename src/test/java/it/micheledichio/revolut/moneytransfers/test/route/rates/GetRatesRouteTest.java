package it.micheledichio.revolut.moneytransfers.test.route.rates;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import org.easymock.EasyMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import com.google.gson.Gson;

import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Country;
import it.micheledichio.revolut.moneytransfers.model.Currency;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.model.Rate;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.rates.GetRatesRoute;
import it.micheledichio.revolut.moneytransfers.service.RateAbstractService;

public class GetRatesRouteTest {

	Gson gson = new Gson();

	@Test
	public void anEmptyRepositoryReturnsNotFound() {
		RateAbstractService rateService = EasyMock.createMock(RateAbstractService.class);
		expect(rateService.getAll()).andReturn(Collections.emptyList());
		replay(rateService);

		GetRatesRoute route = new GetRatesRoute(rateService);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Rates not found"))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(rateService);
	}
	
	@Test
	public void aPopulatedRepositoryReturnsOkAndListOfRates() {
		RateAbstractService rateService = EasyMock.createMock(RateAbstractService.class);
		expect(rateService.getAll()).andReturn(Arrays.asList(new Rate(Long.parseLong("1"), new Currency("EUR", "European Euro", new Country("ITA", "Italy")), new BigDecimal("0.86072"))));
		replay(rateService);

		GetRatesRoute route = new GetRatesRoute(rateService);
		assertEquals(
				new Answer(HttpStatus.OK_200,
						gson.toJson(Arrays.asList(new Rate(Long.parseLong("1"), new Currency("EUR", "European Euro", new Country("ITA", "Italy")), new BigDecimal("0.86072"))))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(rateService);
	}

}

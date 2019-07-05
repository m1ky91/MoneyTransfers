package it.micheledichio.moneytransfers.test.route.currencies;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.easymock.EasyMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import com.google.gson.Gson;

import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Currency;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.route.currencies.GetCurrenciesRoute;
import it.micheledichio.moneytransfers.service.CurrencyAbstractService;

public class GetCurrenciesRouteTest {

	Gson gson = new Gson();

	@Test
	public void anEmptyRepositoryReturnsNotFound() {
		CurrencyAbstractService currencyService = EasyMock.createMock(CurrencyAbstractService.class);
		expect(currencyService.getAll()).andReturn(Collections.emptyList());
		replay(currencyService);

		GetCurrenciesRoute route = new GetCurrenciesRoute(currencyService);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Currencies not found"))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(currencyService);
	}
	
	@Test
	public void aPopulatedRepositoryReturnsOkAndListOfCurrencies() {
		CurrencyAbstractService currencyService = EasyMock.createMock(CurrencyAbstractService.class);
		expect(currencyService.getAll()).andReturn(Arrays.asList(new Currency("EUR", "European Euro", "ITA")));
		replay(currencyService);

		GetCurrenciesRoute route = new GetCurrenciesRoute(currencyService);
		assertEquals(
				new Answer(HttpStatus.OK_200,
						gson.toJson(Arrays.asList(new Currency("EUR", "European Euro", "ITA")))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(currencyService);
	}

}

package it.micheledichio.moneytransfers.test.route.currencies;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import com.google.gson.Gson;

import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Currency;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.route.currencies.GetCurrencyByCodeRoute;
import it.micheledichio.moneytransfers.service.CurrencyAbstractService;

public class GetCurrencyByCodeRouteTest {
	
	Gson gson = new Gson();

	@Test
	public void aWrongCodeOrCodeNotInRepositoryReturnsNotFound() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":code", "32221");
		
		CurrencyAbstractService currencyService = EasyMock.createMock(CurrencyAbstractService.class);
		expect(currencyService.getByCode(urlParams.get(":code"))).andReturn(Collections.emptyList());
		replay(currencyService);

		GetCurrencyByCodeRoute route = new GetCurrencyByCodeRoute(currencyService);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Currencies not found"))),
				route.process(new Empty(), urlParams));
		
		verify(currencyService);
	}
	
	@Test
	public void aCodePresentInRepositoryReturnsOkAndRelatedCurrencies() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":code", "EUR");
		
		CurrencyAbstractService currencyService = EasyMock.createMock(CurrencyAbstractService.class);
		expect(currencyService.getByCode(urlParams.get(":code"))).andReturn(Arrays.asList(new Currency("EUR", "European Euro", "ITA")));
		replay(currencyService);

		GetCurrencyByCodeRoute route = new GetCurrencyByCodeRoute(currencyService);
		assertEquals(
				Answer.ok(gson.toJson(Arrays.asList(new Currency("EUR", "European Euro", "ITA")))),
				route.process(new Empty(), urlParams));
		
		verify(currencyService);
	}

}

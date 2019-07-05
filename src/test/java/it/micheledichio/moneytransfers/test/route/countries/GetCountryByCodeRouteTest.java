package it.micheledichio.moneytransfers.test.route.countries;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import com.google.gson.Gson;

import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Country;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.route.countries.GetCountryByCodeRoute;
import it.micheledichio.moneytransfers.service.CountryAbstractService;

public class GetCountryByCodeRouteTest {
	
	Gson gson = new Gson();

	@Test
	public void aWrongCodeOrCodeNotInRepositoryReturnsNotFound() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":code", "12223");
		
		CountryAbstractService countryService = EasyMock.createMock(CountryAbstractService.class);
		expect(countryService.get(urlParams.get(":code"))).andReturn(null);
		replay(countryService);

		GetCountryByCodeRoute route = new GetCountryByCodeRoute(countryService);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Country not found"))),
				route.process(new Empty(), urlParams));
		
		verify(countryService);
	}
	
	@Test
	public void aCodePresentInRepositoryReturnsOkAndRelatedCountry() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":code", "ITA");
		
		CountryAbstractService countryService = EasyMock.createMock(CountryAbstractService.class);
		expect(countryService.get(urlParams.get(":code"))).andReturn(new Country("ITA", "Italy"));
		replay(countryService);

		GetCountryByCodeRoute route = new GetCountryByCodeRoute(countryService);
		assertEquals(
				Answer.ok(gson.toJson(new Country("ITA", "Italy"))),
				route.process(new Empty(), urlParams));
		
		verify(countryService);
	}

}

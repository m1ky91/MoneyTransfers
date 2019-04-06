package it.micheledichio.revolut.moneytransfers.test.route.countries;

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

import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Country;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.countries.GetCountriesRoute;
import it.micheledichio.revolut.moneytransfers.service.CountryAbstractService;

public class GetCountriesRouteTest {

	Gson gson = new Gson();

	@Test
	public void anEmptyRepositoryReturnsNotFound() {
		CountryAbstractService countryService = EasyMock.createMock(CountryAbstractService.class);
		expect(countryService.getAll()).andReturn(Collections.emptyList());
		replay(countryService);

		GetCountriesRoute route = new GetCountriesRoute(countryService);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Countries not found"))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(countryService);
	}
	
	@Test
	public void aPopulatedRepositoryReturnsOkAndListOfCountries() {
		CountryAbstractService countryService = EasyMock.createMock(CountryAbstractService.class);
		expect(countryService.getAll()).andReturn(Arrays.asList(new Country("ITA", "Italy")));
		replay(countryService);

		GetCountriesRoute route = new GetCountriesRoute(countryService);
		assertEquals(
				new Answer(HttpStatus.OK_200,
						gson.toJson(Arrays.asList(new Country("ITA", "Italy")))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(countryService);
	}

}

package it.micheledichio.revolut.moneytransfers.test.route.rates;


import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import com.google.gson.Gson;

import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.model.Rate;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.rates.GetRateByIdRoute;
import it.micheledichio.revolut.moneytransfers.service.RateAbstractService;

public class GetRateByIdRouteTest {
	
	Gson gson = new Gson();

	@Test
	public void anIdNotInRepositoryReturnsNotFound() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":id", "-1");
		
		RateAbstractService rateService = EasyMock.createMock(RateAbstractService.class);
		expect(rateService.getById(Long.decode(urlParams.get(":id")))).andReturn(null);
		replay(rateService);

		GetRateByIdRoute route = new GetRateByIdRoute(rateService);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Rate not found"))),
				route.process(new Empty(), urlParams));
		
		verify(rateService);
	}
	
	@Test
	public void anIdPresentInRepositoryReturnsOkAndRelatedRate() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":id", "1");
		
		RateAbstractService rateService = EasyMock.createMock(RateAbstractService.class);
		expect(rateService.getById(Long.decode(urlParams.get(":id")))).andReturn(new Rate(Long.parseLong("1"), "EUR", new BigDecimal("0.86072")));
		replay(rateService);

		GetRateByIdRoute route = new GetRateByIdRoute(rateService);
		assertEquals(
				Answer.ok(gson.toJson(new Rate(Long.parseLong("1"), "EUR", new BigDecimal("0.86072")))),
				route.process(new Empty(), urlParams));
		
		verify(rateService);
	}

}

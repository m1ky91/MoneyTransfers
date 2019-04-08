package it.micheledichio.revolut.moneytransfers.test.route.accounts;

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

import it.micheledichio.revolut.moneytransfers.model.Account;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.accounts.GetAccountByNumberRoute;
import it.micheledichio.revolut.moneytransfers.service.AccountAbstractService;

public class GetAccountByNumberRouteTest {
	
	Gson gson = new Gson();

	@Test
	public void aWrongNumberOrNumberNotInRepositoryReturnsNotFound() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":number", "number");
		
		AccountAbstractService accountService = EasyMock.createMock(AccountAbstractService.class);
		expect(accountService.getByNumber(urlParams.get(":number"))).andReturn(null);
		replay(accountService);

		GetAccountByNumberRoute route = new GetAccountByNumberRoute(accountService);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Account not found"))),
				route.process(new Empty(), urlParams));
		
		verify(accountService);
	}
	
	@Test
	public void aCodePresentInRepositoryReturnsOkAndRelatedCountry() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":number", "IT60X0542811101000000123456");
		
		AccountAbstractService accountService = EasyMock.createMock(AccountAbstractService.class);
		expect(accountService.getByNumber(urlParams.get(":number"))).andReturn(new Account("IT60X0542811101000000123456", new BigDecimal("2500"), "EUR", null));
		replay(accountService);

		GetAccountByNumberRoute route = new GetAccountByNumberRoute(accountService);
		assertEquals(
				Answer.ok(gson.toJson(new Account("IT60X0542811101000000123456", new BigDecimal("2500"), "EUR", null))),
				route.process(new Empty(), urlParams));
		
		verify(accountService);
	}

}

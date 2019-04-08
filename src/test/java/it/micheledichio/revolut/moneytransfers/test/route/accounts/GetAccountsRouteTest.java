package it.micheledichio.revolut.moneytransfers.test.route.accounts;

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

import it.micheledichio.revolut.moneytransfers.model.Account;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.accounts.GetAccountsRoute;
import it.micheledichio.revolut.moneytransfers.service.AccountAbstractService;

public class GetAccountsRouteTest {

	Gson gson = new Gson();

	@Test
	public void anEmptyRepositoryReturnsNotFound() {
		AccountAbstractService accountService = EasyMock.createMock(AccountAbstractService.class);
		expect(accountService.getAll()).andReturn(Collections.emptyList());
		replay(accountService);

		GetAccountsRoute route = new GetAccountsRoute(accountService);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Accounts not found"))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(accountService);
	}
	
	@Test
	public void aPopulatedRepositoryReturnsOkAndListOfAccounts() {
		AccountAbstractService accountService = EasyMock.createMock(AccountAbstractService.class);
		expect(accountService.getAll()).andReturn(Arrays.asList(new Account("IT60X0542811101000000123456", new BigDecimal("2500"), "EUR", null)));
		replay(accountService);

		GetAccountsRoute route = new GetAccountsRoute(accountService);
		assertEquals(
				new Answer(HttpStatus.OK_200,
						gson.toJson(Arrays.asList(new Account("IT60X0542811101000000123456", new BigDecimal("2500"), "EUR", null)))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(accountService);
	}

}

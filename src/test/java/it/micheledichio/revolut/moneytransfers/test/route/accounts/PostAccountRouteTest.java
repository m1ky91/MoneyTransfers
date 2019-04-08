package it.micheledichio.revolut.moneytransfers.test.route.accounts;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collections;

import org.easymock.EasyMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import com.google.gson.Gson;

import it.micheledichio.revolut.moneytransfers.model.Account;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.accounts.PostAccountRoute;
import it.micheledichio.revolut.moneytransfers.service.AccountAbstractService;

public class PostAccountRouteTest {
	
	Gson gson = new Gson();
	
	@Test
    public void anEmptyBodyReturnsBadRequest() {
        Account newAccount = null;

        AccountAbstractService service = EasyMock.createMock(AccountAbstractService.class);
        replay(service);

        PostAccountRoute route = new PostAccountRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newAccount, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewAccountWithoutNumberReturnsBadRequest() {
        Account newAccount = new Account();
        newAccount.setCurrency("EUR");
        newAccount.setBalance(new BigDecimal("1500"));
        newAccount.setSortCode("123456");
        assertFalse(newAccount.isValid());

        AccountAbstractService service = EasyMock.createMock(AccountAbstractService.class);
        replay(service);

        PostAccountRoute route = new PostAccountRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newAccount, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewAccountWithAnEmptyNumberReturnsBadRequest() {
        Account newAccount = new Account();
        newAccount.setNumber("");
        newAccount.setCurrency("EUR");
        newAccount.setBalance(new BigDecimal("1500"));
        newAccount.setSortCode("123456");
        assertFalse(newAccount.isValid());

        AccountAbstractService service = EasyMock.createMock(AccountAbstractService.class);
        replay(service);

        PostAccountRoute route = new PostAccountRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newAccount, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewAccountWithoutBalanceReturnsBadRequest() {
        Account newAccount = new Account();
        newAccount.setNumber("123456789");
        newAccount.setCurrency("EUR");
        newAccount.setSortCode("123456");
        assertFalse(newAccount.isValid());

        AccountAbstractService service = EasyMock.createMock(AccountAbstractService.class);
        replay(service);

        PostAccountRoute route = new PostAccountRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newAccount, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewAccountWithABalanceLessThanZeroReturnsBadRequest() {
        Account newAccount = new Account();
        newAccount.setNumber("123456789");
        newAccount.setCurrency("EUR");
        newAccount.setBalance(new BigDecimal("-1"));
        newAccount.setSortCode("123456");
        assertFalse(newAccount.isValid());

        AccountAbstractService service = EasyMock.createMock(AccountAbstractService.class);
        replay(service);

        PostAccountRoute route = new PostAccountRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newAccount, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewAccountWithoutCurrencyReturnsBadRequest() {
        Account newAccount = new Account();
        newAccount.setNumber("123456789");
        newAccount.setBalance(new BigDecimal("1500"));
        newAccount.setSortCode("123456");
        assertFalse(newAccount.isValid());

        AccountAbstractService service = EasyMock.createMock(AccountAbstractService.class);
        replay(service);

        PostAccountRoute route = new PostAccountRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newAccount, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewAccountWithoutSortCodeReturnsBadRequest() {
        Account newAccount = new Account();
        newAccount.setNumber("123456789");
        newAccount.setCurrency("EUR");
        newAccount.setBalance(new BigDecimal("1500"));
        assertFalse(newAccount.isValid());

        AccountAbstractService service = EasyMock.createMock(AccountAbstractService.class);
        replay(service);

        PostAccountRoute route = new PostAccountRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newAccount, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anAccountIsCorrectlyCreatedAndReturnsOk() {
        Account newAccount = new Account();
        newAccount.setNumber("123456789");
        newAccount.setCurrency("EUR");
        newAccount.setBalance(new BigDecimal("1500"));
        newAccount.setSortCode("123456");
        assertTrue(newAccount.isValid());

        AccountAbstractService service = EasyMock.createMock(AccountAbstractService.class);
        expect(service.create(newAccount)).andReturn(newAccount);
        replay(service);

        PostAccountRoute route = new PostAccountRoute(service);
        assertEquals(Answer.ok(gson.toJson(newAccount)), route.process(newAccount, Collections.emptyMap()));

        verify(service);
    }

}

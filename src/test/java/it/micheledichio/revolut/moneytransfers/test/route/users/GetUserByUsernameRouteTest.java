package it.micheledichio.revolut.moneytransfers.test.route.users;

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

import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.User;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.users.GetUserByUsernameRoute;
import it.micheledichio.revolut.moneytransfers.service.UserAbstractService;

public class GetUserByUsernameRouteTest {
	
	Gson gson = new Gson();

	@Test
	public void aWrongUsernameOrUsernameNotInRepositoryReturnsNotFound() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":username", "username");
		
		UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
		expect(service.getByUsername(urlParams.get(":username"))).andReturn(null);
		replay(service);

		GetUserByUsernameRoute route = new GetUserByUsernameRoute(service);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "User not found"))),
				route.process(new Empty(), urlParams));
		
		verify(service);
	}
	
	@Test
	public void anUsernamePresentInRepositoryReturnsOkAndRelatedUser() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":username", "mrossi");
		
		UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
		expect(service.getByUsername(urlParams.get(":username"))).andReturn(new User("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456"));
		replay(service);

		GetUserByUsernameRoute route = new GetUserByUsernameRoute(service);
		assertEquals(
				Answer.ok(gson.toJson(new User("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456"))),
				route.process(new Empty(), urlParams));
		
		verify(service);
	}

}

package it.micheledichio.moneytransfers.test.route.users;

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
import it.micheledichio.moneytransfers.model.User;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.route.users.GetUsersRoute;
import it.micheledichio.moneytransfers.service.UserAbstractService;

public class GetUsersRouteTest {

	Gson gson = new Gson();

	@Test
	public void anEmptyRepositoryReturnsNotFound() {
		UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
		expect(service.getAll()).andReturn(Collections.emptyList());
		replay(service);

		GetUsersRoute route = new GetUsersRoute(service);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Users not found"))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(service);
	}
	
	@Test
	public void aPopulatedRepositoryReturnsOkAndListOfUsers() {
		UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
		expect(service.getAll()).andReturn(Arrays.asList(new User("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456")));
		replay(service);

		GetUsersRoute route = new GetUsersRoute(service);
		assertEquals(
				new Answer(HttpStatus.OK_200,
						gson.toJson(Arrays.asList(new User("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456")))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(service);
	}

}

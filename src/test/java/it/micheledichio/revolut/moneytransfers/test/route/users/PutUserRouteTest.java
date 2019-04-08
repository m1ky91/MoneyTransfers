package it.micheledichio.revolut.moneytransfers.test.route.users;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.easymock.EasyMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import com.google.gson.Gson;

import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.User;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.users.PutUserRoute;
import it.micheledichio.revolut.moneytransfers.service.UserAbstractService;

public class PutUserRouteTest {
	
	Gson gson = new Gson();
	
	@Test
    public void anEmptyBodyReturnsBadRequest() {
        User newUser = null;

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewUserWithoutUsernameReturnsBadRequest() {
        User newUser = new User();
        newUser.setPassword("password");
        newUser.setFirstName("a");
        newUser.setLastName("b");
        assertFalse(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewUserWithAnEmptyUsernameReturnsBadRequest() {
        User newUser = new User();
        newUser.setUsername("");
        newUser.setPassword("password");
        newUser.setFirstName("a");
        newUser.setLastName("b");
        assertFalse(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewUserWithoutPasswordReturnsBadRequest() {
        User newUser = new User();
        newUser.setUsername("ab");
        newUser.setFirstName("a");
        newUser.setLastName("b");
        assertFalse(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewUserWithAnEmptyPasswordReturnsBadRequest() {
        User newUser = new User();
        newUser.setUsername("ab");
        newUser.setPassword("");
        newUser.setFirstName("a");
        newUser.setLastName("b");
        assertFalse(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewUserWithoutFirstNameReturnsBadRequest() {
        User newUser = new User();
        newUser.setUsername("ab");
        newUser.setPassword("password");
        newUser.setLastName("b");
        assertFalse(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewUserWithAnEmptyFirstNameReturnsBadRequest() {
        User newUser = new User();
        newUser.setUsername("ab");
        newUser.setPassword("password");
        newUser.setFirstName("");
        newUser.setLastName("b");
        assertFalse(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewUserWithoutLastNameReturnsBadRequest() {
        User newUser = new User();
        newUser.setUsername("ab");
        newUser.setPassword("password");
        newUser.setFirstName("a");
        assertFalse(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewUserWithAnEmptyLastNameReturnsBadRequest() {
        User newUser = new User();
        newUser.setUsername("ab");
        newUser.setPassword("password");
        newUser.setFirstName("a");
        newUser.setLastName("");
        assertFalse(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anUserNotInRepositoryReturnsNotFound() {
		User newUser = new User();
        newUser.setUsername("ab");
        newUser.setPassword("password");
        newUser.setFirstName("a");
        newUser.setLastName("b");
        assertTrue(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        expect(service.update(newUser)).andReturn(null);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(new Answer(HttpStatus.NOT_FOUND_404, gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "User not found"))), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anUserIsCorrectlyUpdatedAndReturnsOk() {
        User newUser = new User();
        newUser.setUsername("ab");
        newUser.setPassword("password");
        newUser.setFirstName("a");
        newUser.setLastName("b");
        assertTrue(newUser.isValid());

        UserAbstractService service = EasyMock.createMock(UserAbstractService.class);
        expect(service.update(newUser)).andReturn(newUser);
        replay(service);

        PutUserRoute route = new PutUserRoute(service);
        assertEquals(Answer.ok(gson.toJson(newUser)), route.process(newUser, Collections.emptyMap()));

        verify(service);
    }

}

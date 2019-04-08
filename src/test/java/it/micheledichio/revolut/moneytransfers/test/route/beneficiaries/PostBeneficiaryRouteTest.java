package it.micheledichio.revolut.moneytransfers.test.route.beneficiaries;

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
import it.micheledichio.revolut.moneytransfers.model.Beneficiary;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.beneficiaries.PostBeneficiaryRoute;
import it.micheledichio.revolut.moneytransfers.service.BeneficiaryAbstractService;

public class PostBeneficiaryRouteTest {
	
	Gson gson = new Gson();
	
	@Test
    public void anEmptyBodyReturnsBadRequest() {
        Beneficiary newBeneficiary = null;

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewBeneficiaryWithoutUsernameReturnsBadRequest() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setPassword("password");
        newBeneficiary.setFirstName("a");
        newBeneficiary.setLastName("b");
        assertFalse(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewBeneficiaryWithAnEmptyUsernameReturnsBadRequest() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setUsername("");
        newBeneficiary.setPassword("password");
        newBeneficiary.setFirstName("a");
        newBeneficiary.setLastName("b");
        assertFalse(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewBeneficiaryWithoutPasswordReturnsBadRequest() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setUsername("ab");
        newBeneficiary.setFirstName("a");
        newBeneficiary.setLastName("b");
        assertFalse(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewBeneficiaryWithAnEmptyPasswordReturnsBadRequest() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setUsername("ab");
        newBeneficiary.setPassword("");
        newBeneficiary.setFirstName("a");
        newBeneficiary.setLastName("b");
        assertFalse(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewBeneficiaryWithoutFirstNameReturnsBadRequest() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setUsername("ab");
        newBeneficiary.setPassword("password");
        newBeneficiary.setLastName("b");
        assertFalse(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewBeneficiaryWithAnEmptyFirstNameReturnsBadRequest() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setUsername("ab");
        newBeneficiary.setPassword("password");
        newBeneficiary.setFirstName("");
        newBeneficiary.setLastName("b");
        assertFalse(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewBeneficiaryWithoutLastNameReturnsBadRequest() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setUsername("ab");
        newBeneficiary.setPassword("password");
        newBeneficiary.setFirstName("a");
        assertFalse(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewBeneficiaryWithAnEmptyLastNameReturnsBadRequest() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setUsername("ab");
        newBeneficiary.setPassword("password");
        newBeneficiary.setFirstName("a");
        newBeneficiary.setLastName("");
        assertFalse(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anBeneficiaryIsCorrectlyCreatedAndReturnsOk() {
        Beneficiary newBeneficiary = new Beneficiary();
        newBeneficiary.setUsername("ab");
        newBeneficiary.setPassword("password");
        newBeneficiary.setFirstName("a");
        newBeneficiary.setLastName("b");
        assertTrue(newBeneficiary.isValid());

        BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
        expect(service.create(newBeneficiary)).andReturn(newBeneficiary);
        replay(service);

        PostBeneficiaryRoute route = new PostBeneficiaryRoute(service);
        assertEquals(Answer.ok(gson.toJson(newBeneficiary)), route.process(newBeneficiary, Collections.emptyMap()));

        verify(service);
    }

}

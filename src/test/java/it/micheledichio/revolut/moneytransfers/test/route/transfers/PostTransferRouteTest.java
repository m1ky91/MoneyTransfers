package it.micheledichio.revolut.moneytransfers.test.route.transfers;

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

import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Transfer;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.transfers.PostTransferRoute;
import it.micheledichio.revolut.moneytransfers.service.TransferAbstractService;

public class PostTransferRouteTest {
	
	Gson gson = new Gson();
	
	@Test
    public void anEmptyBodyReturnsBadRequest() {
        Transfer newTransfer = null;

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewTransferWithoutAmountReturnsBadRequest() {
		Transfer newTransfer = new Transfer();
		newTransfer.setReference("send 1");
		newTransfer.setSender("ab");
		newTransfer.setBeneficiary("cd");
        assertFalse(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewTransferWithAnAmountLessThanZeroReturnsBadRequest() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("-1"));
		newTransfer.setReference("send 1");
		newTransfer.setSender("ab");
		newTransfer.setBeneficiary("cd");
        assertFalse(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewTransferWithoutReferenceReturnsBadRequest() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setSender("ab");
		newTransfer.setBeneficiary("cd");
        assertFalse(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewTransferWithAnEmptyReferenceReturnsBadRequest() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setReference("");
		newTransfer.setSender("ab");
		newTransfer.setBeneficiary("cd");
        assertFalse(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewTransferWithoutSenderReturnsBadRequest() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setReference("send 1");
		newTransfer.setBeneficiary("cd");
        assertFalse(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewTransferWithAnEmptySenderReturnsBadRequest() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setReference("send 1");
		newTransfer.setSender("");
		newTransfer.setBeneficiary("cd");
        assertFalse(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewTransferWithoutBeneficiaryReturnsBadRequest() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setReference("send 1");
		newTransfer.setSender("ab");
        assertFalse(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anInvalidNewTransferWithAnEmptyBeneficiaryReturnsBadRequest() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setReference("send 1");
		newTransfer.setSender("ab");
		newTransfer.setBeneficiary("");
        assertFalse(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void aTransferIsNotCreatedWhetherASenderDoesNotHaveEnoughFundsAndReturnsOk() {
		Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setReference("send 1");
		newTransfer.setSender("ab");
		newTransfer.setBeneficiary("cd");
        assertTrue(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        expect(service.create(newTransfer)).andReturn(null);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(new Answer(HttpStatus.BAD_REQUEST_400, gson.toJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data"))), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }
	
	@Test
    public void anTransferIsCorrectlyCreatedAndReturnsOk() {
    	Transfer newTransfer = new Transfer();
		newTransfer.setAmount(new BigDecimal("1"));
		newTransfer.setReference("send 1");
		newTransfer.setSender("ab");
		newTransfer.setBeneficiary("cd");
        assertTrue(newTransfer.isValid());

        TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
        expect(service.create(newTransfer)).andReturn(newTransfer);
        replay(service);

        PostTransferRoute route = new PostTransferRoute(service);
        assertEquals(Answer.ok(gson.toJson(newTransfer)), route.process(newTransfer, Collections.emptyMap()));

        verify(service);
    }

}

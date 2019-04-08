package it.micheledichio.revolut.moneytransfers.test.route.transfers;

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
import it.micheledichio.revolut.moneytransfers.model.Transfer;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.transfers.GetTransferByIdRoute;
import it.micheledichio.revolut.moneytransfers.service.TransferAbstractService;

public class GetTransferByIdRouteTest {
	
	Gson gson = new Gson();

	@Test
	public void anIdNotInRepositoryReturnsNotFound() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":id", "-1");
		
		TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
		expect(service.getById(Long.decode(urlParams.get(":id")))).andReturn(null);
		replay(service);

		GetTransferByIdRoute route = new GetTransferByIdRoute(service);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Transfer not found"))),
				route.process(new Empty(), urlParams));
		
		verify(service);
	}
	
	@Test
	public void anIdPresentInRepositoryReturnsOkAndRelatedTransfer() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":id", "1");
		
		TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
		expect(service.getById(Long.decode(urlParams.get(":id")))).andReturn(new Transfer(Long.parseLong("1"), new BigDecimal("80"), "send 1", "ab", "cd"));
		replay(service);

		GetTransferByIdRoute route = new GetTransferByIdRoute(service);
		assertEquals(
				Answer.ok(gson.toJson(new Transfer(Long.parseLong("1"), new BigDecimal("80"), "send 1", "ab", "cd"))),
				route.process(new Empty(), urlParams));
		
		verify(service);
	}

}

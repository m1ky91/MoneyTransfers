package it.micheledichio.moneytransfers.test.route.transfers;

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

import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.model.Transfer;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.route.transfers.GetTransfersRoute;
import it.micheledichio.moneytransfers.service.TransferAbstractService;

public class GetTransfersRouteTest {

	Gson gson = new Gson();

	@Test
	public void anEmptyRepositoryReturnsNotFound() {
		TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
		expect(service.getAll()).andReturn(Collections.emptyList());
		replay(service);

		GetTransfersRoute route = new GetTransfersRoute(service);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Transfers not found"))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(service);
	}
	
	@Test
	public void aPopulatedRepositoryReturnsOkAndListOfTransfers() {
		TransferAbstractService service = EasyMock.createMock(TransferAbstractService.class);
		expect(service.getAll()).andReturn(Arrays.asList(new Transfer(Long.parseLong("1"), new BigDecimal("80"), "send 1", "ab", "cd")));
		replay(service);

		GetTransfersRoute route = new GetTransfersRoute(service);
		assertEquals(
				new Answer(HttpStatus.OK_200,
						gson.toJson(Arrays.asList(new Transfer(Long.parseLong("1"), new BigDecimal("80"), "send 1", "ab", "cd")))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(service);
	}

}

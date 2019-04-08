package it.micheledichio.revolut.moneytransfers.test.route.beneficiaries;

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
import it.micheledichio.revolut.moneytransfers.model.Beneficiary;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.beneficiaries.GetBeneficiaryByUsernameRoute;
import it.micheledichio.revolut.moneytransfers.service.BeneficiaryAbstractService;

public class GetBeneficiaryByUsernameRouteTest {
	
	Gson gson = new Gson();

	@Test
	public void aWrongUsernameOrUsernameNotInRepositoryReturnsNotFound() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":username", "username");
		
		BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
		expect(service.getByUsername(urlParams.get(":username"))).andReturn(null);
		replay(service);

		GetBeneficiaryByUsernameRoute route = new GetBeneficiaryByUsernameRoute(service);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Beneficiary not found"))),
				route.process(new Empty(), urlParams));
		
		verify(service);
	}
	
	@Test
	public void anUsernamePresentInRepositoryReturnsOkAndRelatedBeneficiary() {
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put(":username", "mrossi");
		
		BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
		expect(service.getByUsername(urlParams.get(":username"))).andReturn(new Beneficiary("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456"));
		replay(service);

		GetBeneficiaryByUsernameRoute route = new GetBeneficiaryByUsernameRoute(service);
		assertEquals(
				Answer.ok(gson.toJson(new Beneficiary("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456"))),
				route.process(new Empty(), urlParams));
		
		verify(service);
	}

}

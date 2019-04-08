package it.micheledichio.revolut.moneytransfers.test.route.beneficiaries;

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

import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Beneficiary;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.route.beneficiaries.GetBeneficiariesRoute;
import it.micheledichio.revolut.moneytransfers.service.BeneficiaryAbstractService;

public class GetBeneficiariesRouteTest {

	Gson gson = new Gson();

	@Test
	public void anEmptyRepositoryReturnsNotFound() {
		BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
		expect(service.getAll()).andReturn(Collections.emptyList());
		replay(service);

		GetBeneficiariesRoute route = new GetBeneficiariesRoute(service);
		assertEquals(
				new Answer(HttpStatus.NOT_FOUND_404,
						gson.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "Beneficiaries not found"))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(service);
	}
	
	@Test
	public void aPopulatedRepositoryReturnsOkAndListOfBeneficiaries() {
		BeneficiaryAbstractService service = EasyMock.createMock(BeneficiaryAbstractService.class);
		expect(service.getAll()).andReturn(Arrays.asList(new Beneficiary("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456")));
		replay(service);

		GetBeneficiariesRoute route = new GetBeneficiariesRoute(service);
		assertEquals(
				new Answer(HttpStatus.OK_200,
						gson.toJson(Arrays.asList(new Beneficiary("mrossi", "password1", "Mario", "Rossi", "IT60X0542811101000000123456")))),
				route.process(new Empty(), Collections.emptyMap()));
		
		verify(service);
	}

}

package it.micheledichio.revolut.moneytransfers.route.beneficiaries;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.micheledichio.revolut.moneytransfers.model.Account;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Beneficiary;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.BeneficiaryAbstractService;
import it.micheledichio.revolut.moneytransfers.service.BeneficiaryService;

@Api("beneficiaries")
@Path("/beneficiaries")
@Produces("application/json")
public class PostBeneficiaryRoute extends AbstractRequestHandler<Beneficiary> {
	
	private final Logger log = LoggerFactory.getLogger(PostBeneficiaryRoute.class);
	
	private BeneficiaryAbstractService service;
	
	public PostBeneficiaryRoute() {
		super(Beneficiary.class);
		service = new BeneficiaryService();
	}
	
	public PostBeneficiaryRoute(BeneficiaryAbstractService service) {
		super(Beneficiary.class);
		this.service = service;
	}

	@POST
	@ApiOperation(value = "Create a beneficiary", nickname = "PostAccountRoute")
	@ApiImplicitParams({ //
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.revolut.moneytransfers.model.Beneficiary", paramType = "body") //
	}) //
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Account.class), //
			@ApiResponse(code = 400, message = "Invalid input data", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Beneficiary value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint POST /beneficiaries called");
		var beneficiary = service.create(value);	
		
		return Answer.ok(dataToJson(beneficiary));
	}

}

package it.micheledichio.revolut.moneytransfers.route.beneficiaries;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Beneficiary;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.BeneficiaryAbstractService;
import it.micheledichio.revolut.moneytransfers.service.BeneficiaryService;

@Api("beneficiaries")
@Path("/beneficiaries/{username}")
@Produces("application/json")
public class GetBeneficiaryByUsernameRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetBeneficiaryByUsernameRoute.class);
	
	private BeneficiaryAbstractService service;
	
	public GetBeneficiaryByUsernameRoute() {
		super(Empty.class);
		service = new BeneficiaryService();
	}
	
	public GetBeneficiaryByUsernameRoute(BeneficiaryAbstractService service) {
		super(Empty.class);
		this.service = service;
	}
	
	@GET
	@ApiOperation(value = "Get information of a beneficiary by his username", nickname = "GetBeneficiaryByUsernameRoute")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "username", value = "Username", required = true, dataType = "string", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Beneficiary.class), //
			@ApiResponse(code = 404, message = "Beneficiary not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /beneficiaries/{number} called");
		
		Beneficiary beneficiary = service.getByUsername(urlParams.get(":username"));

		if (beneficiary == null) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Beneficiary not found")));
		else 
			return Answer.ok(dataToJson(beneficiary));
	}

}

package it.micheledichio.revolut.moneytransfers.route.rates;

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
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.model.Rate;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.RateAbstractService;
import it.micheledichio.revolut.moneytransfers.service.RateService;

@Api("rates")
@Path("/rates/{id}")
@Produces("application/json")
public class GetRateByIdRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetRateByIdRoute.class);
	
	private RateAbstractService service;
	
	public GetRateByIdRoute() {
		super(Empty.class);
		service = new RateService();
	}
	
	public GetRateByIdRoute(RateAbstractService service) {
		super(Empty.class);
		this.service = service;
	}
	
	@GET
	@ApiOperation(value = "Get information of a rate by his id", nickname = "GetRateByIdRoute")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "integer", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Rate.class), //
			@ApiResponse(code = 404, message = "Rate not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint /rates/{id} called");
		Rate rate = service.getById(Long.parseLong(urlParams.get(":id")));

		if (rate == null) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Rate not found")));
		else 
			return Answer.ok(dataToJson(rate));
	}

}

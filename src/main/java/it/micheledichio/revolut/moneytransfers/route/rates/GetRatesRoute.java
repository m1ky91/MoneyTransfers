package it.micheledichio.revolut.moneytransfers.route.rates;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
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
@Path("/rates")
@Produces("application/json")
public class GetRatesRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetRatesRoute.class);
	
	private RateAbstractService service;
	
	public GetRatesRoute() {
		super(Empty.class);
		service = new RateService();
	}
	
	public GetRatesRoute(RateAbstractService service) {
		super(Empty.class);
		this.service = service;
	}

	@GET
	@ApiOperation(value = "Get all rates details", nickname = "GetRatesRoute")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Rate.class, responseContainer = "List"), //
			@ApiResponse(code = 404, message = "Rates not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint /rates called");
		var rates = service.getAll();	
		
		if (rates.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Rates not found")));
		else 
			return Answer.ok(dataToJson(rates));
	}

}

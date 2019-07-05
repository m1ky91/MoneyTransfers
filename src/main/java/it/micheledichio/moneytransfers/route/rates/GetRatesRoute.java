package it.micheledichio.moneytransfers.route.rates;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.model.Rate;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.RateAbstractService;
import it.micheledichio.moneytransfers.service.RateService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

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
		log.info("Endpoint GET /rates called");
		var rates = service.getAll();	
		
		if (rates.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Rates not found")));
		else 
			return Answer.ok(dataToJson(rates));
	}

}

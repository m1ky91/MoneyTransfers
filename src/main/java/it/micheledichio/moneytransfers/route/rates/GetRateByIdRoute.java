package it.micheledichio.moneytransfers.route.rates;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.model.Rate;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.RateAbstractService;
import it.micheledichio.moneytransfers.service.RateService;
import it.micheledichio.moneytransfers.util.HandleParam;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

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
		log.info("Endpoint GET /rates/{id} called");
		HandleParam handleParam = new HandleParam();
		Long number = handleParam.parseLongWithDefault(urlParams.get(":id"), Long.parseLong("-1"));
		
		Rate rate = service.getById(number);

		if (rate == null) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Rate not found")));
		else 
			return Answer.ok(dataToJson(rate));
	}

}

package it.micheledichio.moneytransfers.route.currencies;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Currency;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.CurrencyAbstractService;
import it.micheledichio.moneytransfers.service.CurrencyService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Api("currencies")
@Path("/currencies")
@Produces("application/json")
public class GetCurrenciesRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetCurrenciesRoute.class);
	
	private CurrencyAbstractService service;
	
	public GetCurrenciesRoute() {
		super(Empty.class);
		service = new CurrencyService();
	}
	
	public GetCurrenciesRoute(CurrencyAbstractService service) {
		super(Empty.class);
		this.service = service;
	}

	@GET
	@ApiOperation(value = "Get all currencies details", nickname = "GetCurrencyRoute")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Currency.class, responseContainer = "List"), //
			@ApiResponse(code = 404, message = "Currencies not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /currencies called");
		var currencies = service.getAll();	
		
		if (currencies.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Currencies not found")));
		else 
			return Answer.ok(dataToJson(currencies));
	}

}

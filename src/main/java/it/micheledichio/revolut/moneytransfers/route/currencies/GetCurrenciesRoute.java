package it.micheledichio.revolut.moneytransfers.route.currencies;

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
import it.micheledichio.revolut.moneytransfers.model.Currency;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.CurrencyAbstractService;
import it.micheledichio.revolut.moneytransfers.service.CurrencyService;

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
		log.info("Endpoint /currencies called");
		var currencies = service.getAll();	
		
		if (currencies.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Currencies not found")));
		else 
			return Answer.ok(dataToJson(currencies));
	}

}

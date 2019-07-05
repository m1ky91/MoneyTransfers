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
@Path("/currencies/{code}")
@Produces("application/json")
public class GetCurrencyByCodeRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetCurrencyByCodeRoute.class);
	
	private CurrencyAbstractService service;
	
	public GetCurrencyByCodeRoute() {
		super(Empty.class);
		service = new CurrencyService();
	}
	
	public GetCurrencyByCodeRoute(CurrencyAbstractService service) {
		super(Empty.class);
		this.service = service;
	}
	
	@GET
	@ApiOperation(value = "Get informations of currencies for a given ISO 4217 code", nickname = "GetCurrencyByCodeRoute")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "code", value = "Currency ISO 4217 code", required = true, dataType = "string", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Currency.class, responseContainer = "List"), //
			@ApiResponse(code = 404, message = "Currencies not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /currencies/{code} called");
		var currencies = service.getByCode(urlParams.get(":code"));

		if (currencies.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Currencies not found")));
		else 
			return Answer.ok(dataToJson(currencies));
	}

}

package it.micheledichio.revolut.moneytransfers.route.countries;

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
import it.micheledichio.revolut.moneytransfers.model.Country;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.CountryAbstractService;
import it.micheledichio.revolut.moneytransfers.service.CountryService;

@Api("countries")
@Path("/countries")
@Produces("application/json")
public class GetCountriesRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetCountriesRoute.class);
	
	private CountryAbstractService service;
	
	public GetCountriesRoute() {
		super(Empty.class);
		service = new CountryService();
	}
	
	public GetCountriesRoute(CountryAbstractService service) {
		super(Empty.class);
		this.service = service;
	}

	@GET
	@ApiOperation(value = "Get all countries details", nickname = "GetCountriesRoute")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Country.class, responseContainer = "List"), //
			@ApiResponse(code = 404, message = "Countries not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint /countries called");
		var countries = service.getAll();	
		
		if (countries.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Countries not found")));
		else 
			return Answer.ok(dataToJson(countries));
	}

}

package it.micheledichio.moneytransfers.route.countries;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Country;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.CountryAbstractService;
import it.micheledichio.moneytransfers.service.CountryService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

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
		log.info("Endpoint GET /countries called");
		var countries = service.getAll();	
		
		if (countries.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Countries not found")));
		else 
			return Answer.ok(dataToJson(countries));
	}

}

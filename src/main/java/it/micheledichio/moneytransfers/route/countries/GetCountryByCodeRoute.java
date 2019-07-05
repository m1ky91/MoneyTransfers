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
@Path("/countries/{code}")
@Produces("application/json")
public class GetCountryByCodeRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetCountryByCodeRoute.class);
	
	private CountryAbstractService service;
	
	public GetCountryByCodeRoute() {
		super(Empty.class);
		service = new CountryService();
	}
	
	public GetCountryByCodeRoute(CountryAbstractService service) {
		super(Empty.class);
		this.service = service;
	}
	
	@GET
	@ApiOperation(value = "Get information of a country by his ISO 3166-1 alpha-3 code", nickname = "GetCountryByCodeRoute")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "code", value = "Country ISO 3166-1 alpha-3 code", required = true, dataType = "string", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Country.class), //
			@ApiResponse(code = 404, message = "Country not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /countries/{code} called");
		Country country = service.get(urlParams.get(":code"));

		if (country == null)
			return new Answer(HttpStatus.NOT_FOUND_404,
					dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Country not found")));
		else
			return Answer.ok(dataToJson(country));
	}

}

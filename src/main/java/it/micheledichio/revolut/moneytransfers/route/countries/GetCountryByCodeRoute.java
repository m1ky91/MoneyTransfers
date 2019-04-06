package it.micheledichio.revolut.moneytransfers.route.countries;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.jetty.http.HttpStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@Path("/countries/{code}")
@Produces("application/json")
public class GetCountryByCodeRoute extends AbstractRequestHandler<Empty> {
	
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
		Country country = service.get(urlParams.get(":code"));

		if (country == null)
			return new Answer(HttpStatus.NOT_FOUND_404,
					dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Country not found")));
		else
			return Answer.ok(dataToJson(country));
	}

}
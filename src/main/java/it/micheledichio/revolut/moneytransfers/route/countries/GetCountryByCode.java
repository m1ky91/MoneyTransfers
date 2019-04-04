package it.micheledichio.revolut.moneytransfers.route.countries;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Country;
import spark.Request;
import spark.Response;
import spark.Route;

@Api("countries")
@Path("/countries/{code}")
@Produces("application/json")
public class GetCountryByCode implements Route {
	
	@GET
	@ApiOperation(value = "Get information of a country by his ISO 3166-1 alpha-3 code", nickname = "GetCountryByCode")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "code", value = "Country ISO 3166-1 alpha-3 code", required = true, dataType = "string", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Country.class), //
			@ApiResponse(code = 400, message = "Invalid input data", response = ApiError.class), //
			@ApiResponse(code = 404, message = "Country not found", response = ApiError.class) //
	})
	public String handle(@ApiParam(hidden = true) Request request, @ApiParam(hidden = true) Response response)
			throws Exception {
		String paramId = request.queryParams("id");
		System.out.printf("Path %s - parameter %s\n", GetCountryByCode.class.getName(), paramId);
		response.type("application/json");
		Country country = new Country();
		country.setCode("ITA");
		country.setName("Italy");
		return new Gson().toJson(country);
	}

}

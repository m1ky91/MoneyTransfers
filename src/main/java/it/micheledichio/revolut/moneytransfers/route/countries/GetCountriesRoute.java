package it.micheledichio.revolut.moneytransfers.route.countries;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
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
@Path("/countries")
@Produces("application/json")
public class GetCountriesRoute implements Route {

	@GET
	@ApiOperation(value = "Gets all countries details", nickname = "GetCountriesRoute")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = Country.class, responseContainer = "List"), //
			@ApiResponse(code = 400, message = "Invalid input data", response = ApiError.class), //
			@ApiResponse(code = 401, message = "Unauthorized", response = ApiError.class), //
			@ApiResponse(code = 404, message = "User not found", response = ApiError.class) //
})
	public List<Country> handle(@ApiParam(hidden = true) Request request, @ApiParam(hidden = true) Response response) throws Exception {
		String paramId = request.queryParams("id");
		System.out.printf("Path %s - parameter %s\n", GetCountriesRoute.class.getName(), paramId);
		return new LinkedList<Country>();
}

}

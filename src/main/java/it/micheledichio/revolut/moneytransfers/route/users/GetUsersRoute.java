package it.micheledichio.revolut.moneytransfers.route.users;

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
import it.micheledichio.revolut.moneytransfers.model.User;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.UserAbstractService;
import it.micheledichio.revolut.moneytransfers.service.UserService;

@Api("users")
@Path("/users")
@Produces("application/json")
public class GetUsersRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetUsersRoute.class);
	
	private UserAbstractService service;
	
	public GetUsersRoute() {
		super(Empty.class);
		service = new UserService();
	}
	
	public GetUsersRoute(UserAbstractService service) {
		super(Empty.class);
		this.service = service;
	}

	@GET
	@ApiOperation(value = "Get all users details", nickname = "GetUsersRoute")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = User.class, responseContainer = "List"), //
			@ApiResponse(code = 404, message = "Users not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /users called");
		var users = service.getAll();	
		
		if (users.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Users not found")));
		else 
			return Answer.ok(dataToJson(users));
	}

}

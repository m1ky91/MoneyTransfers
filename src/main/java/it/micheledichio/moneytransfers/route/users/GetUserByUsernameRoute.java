package it.micheledichio.moneytransfers.route.users;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.model.User;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.UserAbstractService;
import it.micheledichio.moneytransfers.service.UserService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Api("users")
@Path("/users/{username}")
@Produces("application/json")
public class GetUserByUsernameRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetUserByUsernameRoute.class);
	
	private UserAbstractService service;
	
	public GetUserByUsernameRoute() {
		super(Empty.class);
		service = new UserService();
	}
	
	public GetUserByUsernameRoute(UserAbstractService service) {
		super(Empty.class);
		this.service = service;
	}
	
	@GET
	@ApiOperation(value = "Get information of a user by his username", nickname = "GetUserByUsernameRoute")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "username", value = "Username", required = true, dataType = "string", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = User.class), //
			@ApiResponse(code = 404, message = "User not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /users/{username} called");
		
		User user = service.getByUsername(urlParams.get(":username"));

		if (user == null) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "User not found")));
		else 
			return Answer.ok(dataToJson(user));
	}

}

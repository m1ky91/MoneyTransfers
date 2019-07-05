package it.micheledichio.moneytransfers.route.users;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.User;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.UserAbstractService;
import it.micheledichio.moneytransfers.service.UserService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Api("users")
@Path("/users")
@Produces("application/json")
public class PutUserRoute extends AbstractRequestHandler<User> {
	
	private final Logger log = LoggerFactory.getLogger(PutUserRoute.class);
	
	private UserAbstractService service;
	
	public PutUserRoute() {
		super(User.class);
		service = new UserService();
	}
	
	public PutUserRoute(UserAbstractService service) {
		super(User.class);
		this.service = service;
	}

	@PUT
	@ApiOperation(value = "Update an user", nickname = "PutUserRoute")
	@ApiImplicitParams({ //
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.moneytransfers.model.User", paramType = "body") //
	}) //
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = User.class), //
			@ApiResponse(code = 400, message = "Invalid input data", response = ApiError.class), //
			@ApiResponse(code = 404, message = "User not found", response = ApiError.class) 
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) User value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint PUT /users called");
		var user = service.update(value);	
		
		if (user == null) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "User not found")));
		else 
			return Answer.ok(dataToJson(user));
	}

}

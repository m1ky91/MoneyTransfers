package it.micheledichio.moneytransfers.route.users;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.User;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.UserAbstractService;
import it.micheledichio.moneytransfers.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Api("users")
@Path("/users")
@Produces("application/json")
public class PostUserRoute extends AbstractRequestHandler<User> {
	
	private final Logger log = LoggerFactory.getLogger(PostUserRoute.class);
	
	private UserAbstractService service;
	
	public PostUserRoute() {
		super(User.class);
		service = new UserService();
	}
	
	public PostUserRoute(UserAbstractService service) {
		super(User.class);
		this.service = service;
	}

	@POST
	@ApiOperation(value = "Create an user", nickname = "PostUserRoute")
	@ApiImplicitParams({ //
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.moneytransfers.model.User", paramType = "body") //
	}) //
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = User.class), //
			@ApiResponse(code = 400, message = "Invalid input data", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) User value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint POST /users called");
		var user = service.create(value);	
		
		return Answer.ok(dataToJson(user));
	}

}

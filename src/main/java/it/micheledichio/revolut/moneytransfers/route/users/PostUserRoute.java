package it.micheledichio.revolut.moneytransfers.route.users;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.User;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.UserAbstractService;
import it.micheledichio.revolut.moneytransfers.service.UserService;

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
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.revolut.moneytransfers.model.User", paramType = "body") //
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

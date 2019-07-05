package it.micheledichio.moneytransfers.route.accounts;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.Account;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.AccountAbstractService;
import it.micheledichio.moneytransfers.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Api("accounts")
@Path("/accounts")
@Produces("application/json")
public class PostAccountRoute extends AbstractRequestHandler<Account> {
	
	private final Logger log = LoggerFactory.getLogger(PostAccountRoute.class);
	
	private AccountAbstractService service;
	
	public PostAccountRoute() {
		super(Account.class);
		service = new AccountService();
	}
	
	public PostAccountRoute(AccountAbstractService service) {
		super(Account.class);
		this.service = service;
	}

	@POST
	@ApiOperation(value = "Create a bank account", nickname = "PostAccountRoute")
	@ApiImplicitParams({ //
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.moneytransfers.model.Account", paramType = "body") //
	}) //
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Account.class), //
			@ApiResponse(code = 400, message = "Invalid input data", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Account value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint POST /accounts called");
		var account = service.create(value);	
		
		return Answer.ok(dataToJson(account));
	}

}

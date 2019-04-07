package it.micheledichio.revolut.moneytransfers.route.accounts;

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
import it.micheledichio.revolut.moneytransfers.model.Account;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.AccountAbstractService;
import it.micheledichio.revolut.moneytransfers.service.AccountService;

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
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.revolut.moneytransfers.model.Account", paramType = "body") //
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

package it.micheledichio.moneytransfers.route.accounts;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.Account;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.AccountAbstractService;
import it.micheledichio.moneytransfers.service.AccountService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Api("accounts")
@Path("/accounts")
@Produces("application/json")
public class PutAccountRoute extends AbstractRequestHandler<Account> {
	
	private final Logger log = LoggerFactory.getLogger(PutAccountRoute.class);
	
	private AccountAbstractService service;
	
	public PutAccountRoute() {
		super(Account.class);
		service = new AccountService();
	}
	
	public PutAccountRoute(AccountAbstractService service) {
		super(Account.class);
		this.service = service;
	}

	@PUT
	@ApiOperation(value = "Update a bank account", nickname = "PutAccountRoute")
	@ApiImplicitParams({ //
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.moneytransfers.model.Account", paramType = "body") //
	}) //
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Account.class), //
			@ApiResponse(code = 400, message = "Invalid input data", response = ApiError.class), //
			@ApiResponse(code = 404, message = "Account not found", response = ApiError.class) 
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Account value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint PUT /accounts called");
		var account = service.update(value);	
		
		if (account == null) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Account not found")));
		else 
			return Answer.ok(dataToJson(account));
	}

}

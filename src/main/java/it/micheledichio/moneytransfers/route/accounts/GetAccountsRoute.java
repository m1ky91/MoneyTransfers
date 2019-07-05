package it.micheledichio.moneytransfers.route.accounts;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.Account;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.AccountAbstractService;
import it.micheledichio.moneytransfers.service.AccountService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Api("accounts")
@Path("/accounts")
@Produces("application/json")
public class GetAccountsRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetAccountsRoute.class);
	
	private AccountAbstractService service;
	
	public GetAccountsRoute() {
		super(Empty.class);
		service = new AccountService();
	}
	
	public GetAccountsRoute(AccountAbstractService service) {
		super(Empty.class);
		this.service = service;
	}

	@GET
	@ApiOperation(value = "Get all accounts details", nickname = "GetAccountsRoute")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Account.class, responseContainer = "List"), //
			@ApiResponse(code = 404, message = "Accounts not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /accounts called");
		var accounts = service.getAll();	
		
		if (accounts.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Accounts not found")));
		else 
			return Answer.ok(dataToJson(accounts));
	}

}

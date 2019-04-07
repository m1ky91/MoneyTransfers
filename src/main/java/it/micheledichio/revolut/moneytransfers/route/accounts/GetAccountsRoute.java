package it.micheledichio.revolut.moneytransfers.route.accounts;

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
import it.micheledichio.revolut.moneytransfers.model.Account;
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.AccountAbstractService;
import it.micheledichio.revolut.moneytransfers.service.AccountService;

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

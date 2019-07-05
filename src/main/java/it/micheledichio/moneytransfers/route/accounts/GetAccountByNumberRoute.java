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
@Path("/accounts/{number}")
@Produces("application/json")
public class GetAccountByNumberRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetAccountByNumberRoute.class);
	
	private AccountAbstractService service;
	
	public GetAccountByNumberRoute() {
		super(Empty.class);
		service = new AccountService();
	}
	
	public GetAccountByNumberRoute(AccountAbstractService service) {
		super(Empty.class);
		this.service = service;
	}
	
	@GET
	@ApiOperation(value = "Get information of an account by his number", nickname = "GetAccountByNumberRoute")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "number", value = "Account number", required = true, dataType = "string", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Account.class), //
			@ApiResponse(code = 404, message = "Account not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /accounts/{number} called");
		
		Account account = service.getByNumber(urlParams.get(":number"));

		if (account == null) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Account not found")));
		else 
			return Answer.ok(dataToJson(account));
	}

}

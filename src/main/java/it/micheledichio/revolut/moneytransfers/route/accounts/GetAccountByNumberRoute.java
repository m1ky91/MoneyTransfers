package it.micheledichio.revolut.moneytransfers.route.accounts;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.jetty.http.HttpStatus;
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
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.AccountAbstractService;
import it.micheledichio.revolut.moneytransfers.service.AccountService;

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

package it.micheledichio.revolut.moneytransfers.route.transfers;

import java.util.Map;

import javax.ws.rs.POST;
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
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Transfer;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.TransferAbstractService;
import it.micheledichio.revolut.moneytransfers.service.TransferService;

@Api("transfers")
@Path("/transfers")
@Produces("application/json")
public class PostTransferRoute extends AbstractRequestHandler<Transfer> {
	
	private final Logger log = LoggerFactory.getLogger(PostTransferRoute.class);
	
	private TransferAbstractService service;
	
	public PostTransferRoute() {
		super(Transfer.class);
		service = new TransferService();
	}
	
	public PostTransferRoute(TransferAbstractService service) {
		super(Transfer.class);
		this.service = service;
	}

	@POST
	@ApiOperation(value = "Create a transfer", nickname = "PostTransferRoute")
	@ApiImplicitParams({ //
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.revolut.moneytransfers.model.Transfer", paramType = "body") //
	}) //
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Transfer.class), //
			@ApiResponse(code = 400, message = "Invalid input data", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Transfer value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint POST /transfers called");
		var transfer = service.create(value);	
		
		if (transfer == null) 			
			return new Answer(HttpStatus.BAD_REQUEST_400, dataToJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data")));
		else 
			return Answer.ok(dataToJson(transfer));
	}

}

package it.micheledichio.moneytransfers.route.transfers;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Transfer;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.TransferAbstractService;
import it.micheledichio.moneytransfers.service.TransferService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

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
		@ApiImplicitParam(required = true, dataType = "it.micheledichio.moneytransfers.model.Transfer", paramType = "body") //
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

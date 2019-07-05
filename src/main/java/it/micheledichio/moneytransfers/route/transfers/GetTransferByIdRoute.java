package it.micheledichio.moneytransfers.route.transfers;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.model.Transfer;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.TransferAbstractService;
import it.micheledichio.moneytransfers.service.TransferService;
import it.micheledichio.moneytransfers.util.HandleParam;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Api("transfers")
@Path("/transfers/{id}")
@Produces("application/json")
public class GetTransferByIdRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetTransferByIdRoute.class);
	
	private TransferAbstractService service;
	
	public GetTransferByIdRoute() {
		super(Empty.class);
		service = new TransferService();
	}
	
	public GetTransferByIdRoute(TransferAbstractService service) {
		super(Empty.class);
		this.service = service;
	}
	
	@GET
	@ApiOperation(value = "Get information of a transfer by his id", nickname = "GetTransferByIdRoute")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "Id", required = true, dataType = "integer", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Transfer.class), //
			@ApiResponse(code = 404, message = "Transfer not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /transfers/{id} called");
		HandleParam handleParam = new HandleParam();
		Long number = handleParam.parseLongWithDefault(urlParams.get(":id"), Long.parseLong("-1"));
		
		Transfer transfer = service.getById(number);

		if (transfer == null) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Transfer not found")));
		else 
			return Answer.ok(dataToJson(transfer));
	}

}

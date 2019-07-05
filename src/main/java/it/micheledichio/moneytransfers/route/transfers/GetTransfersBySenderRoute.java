package it.micheledichio.moneytransfers.route.transfers;

import io.swagger.annotations.*;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.model.Transfer;
import it.micheledichio.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.moneytransfers.route.Answer;
import it.micheledichio.moneytransfers.service.TransferAbstractService;
import it.micheledichio.moneytransfers.service.TransferService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Map;

@Api("transfers")
@Path("/transfers/sender/{username}")
@Produces("application/json")
public class GetTransfersBySenderRoute extends AbstractRequestHandler<Empty> {
	
	private final Logger log = LoggerFactory.getLogger(GetTransfersBySenderRoute.class);
	
	private TransferAbstractService service;
	
	public GetTransfersBySenderRoute() {
		super(Empty.class);
		service = new TransferService();
	}
	
	public GetTransfersBySenderRoute(TransferAbstractService service) {
		super(Empty.class);
		this.service = service;
	}
	
	@GET
	@ApiOperation(value = "Get informations of transfers for a given sender", nickname = "GetTransfersBySenderRoute")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "username", value = "Sender username", required = true, dataType = "string", paramType = "path")
	  })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Transfer.class, responseContainer = "List"), //
			@ApiResponse(code = 404, message = "Transfers not found", response = ApiError.class) //
	})
	@Override
	public Answer processImpl(@ApiParam(hidden = true) Empty value, @ApiParam(hidden = true) Map<String, String> urlParams) {
		log.info("Endpoint GET /transfers/sender/{username} called");
		
		List<Transfer> transfers = service.getBySender(urlParams.get(":username"));

		if (transfers.isEmpty()) 			
			return new Answer(HttpStatus.NOT_FOUND_404, dataToJson(new ApiError(HttpStatus.NOT_FOUND_404, "Transfers not found")));
		else 
			return Answer.ok(dataToJson(transfers));
	}

}

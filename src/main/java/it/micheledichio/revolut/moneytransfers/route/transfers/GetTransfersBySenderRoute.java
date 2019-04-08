package it.micheledichio.revolut.moneytransfers.route.transfers;

import java.util.List;
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
import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Empty;
import it.micheledichio.revolut.moneytransfers.model.Transfer;
import it.micheledichio.revolut.moneytransfers.route.AbstractRequestHandler;
import it.micheledichio.revolut.moneytransfers.route.Answer;
import it.micheledichio.revolut.moneytransfers.service.TransferAbstractService;
import it.micheledichio.revolut.moneytransfers.service.TransferService;

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

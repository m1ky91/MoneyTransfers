package it.micheledichio.revolut.moneytransfers.route.transfers;

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
import it.micheledichio.revolut.moneytransfers.util.HandleParam;

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

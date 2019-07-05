package it.micheledichio.moneytransfers.route;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import it.micheledichio.moneytransfers.model.ApiError;
import it.micheledichio.moneytransfers.model.Empty;
import it.micheledichio.moneytransfers.model.Validable;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

public abstract class AbstractRequestHandler<V extends Validable> implements RequestHandler<V>, Route {
	
	private final Logger log = LoggerFactory.getLogger(AbstractRequestHandler.class);

	private Class<V> valueClass;

	public AbstractRequestHandler(Class<V> valueClass) {
		this.valueClass = valueClass;
	}

	public static String dataToJson(Object data) {
		Gson mapper = new Gson();
		return mapper.toJson(data);
	}

	public final Answer process(V value, Map<String, String> urlParams) {
		if (value != null && !value.isValid()) 
			return new Answer(HttpStatus.BAD_REQUEST_400, dataToJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data")));
		else if (value == null && (valueClass != Empty.class))
			return new Answer(HttpStatus.BAD_REQUEST_400, dataToJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data")));
		else
			return processImpl(value, urlParams);
	}

	public abstract Answer processImpl(V value, Map<String, String> urlParams);

	@Override
	public Object handle(Request request, Response response) throws Exception {
		log.info("Request from IP: " + request.ip());
		
		try {
			Gson objectMapper = new Gson();
			V value = null;
			if (valueClass != Empty.class)
				value = objectMapper.fromJson(request.body(), valueClass);
			Map<String, String> urlParams = request.params();
			Answer answer = process(value, urlParams);
			response.status(answer.getCode());
			response.type("application/json");
			response.body(answer.getBody());
			return answer.getBody();
		} catch (JsonSyntaxException e) {
			Answer answer = new Answer(HttpStatus.BAD_REQUEST_400, dataToJson(new ApiError(HttpStatus.BAD_REQUEST_400, "Invalid input data")));
			response.status(answer.getCode());
			response.type("application/json");
			response.body(answer.getBody());			
			return answer.getBody();
		}
	}

}

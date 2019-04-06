package it.micheledichio.revolut.moneytransfers.route;

import java.util.Map;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;

import it.micheledichio.revolut.moneytransfers.model.ApiError;
import it.micheledichio.revolut.moneytransfers.model.Validable;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class AbstractRequestHandler<V extends Validable> implements RequestHandler<V>, Route {

	private Class<V> valueClass;

	public AbstractRequestHandler(Class<V> valueClass) {
		this.valueClass = valueClass;
	}

	public static String dataToJson(Object data) {
		Gson mapper = new Gson();
		return mapper.toJson(data);
	}

	public final Answer process(V value, Map<String, String> urlParams) {
		if (!value.isValid()) 
			return new Answer(HttpStatus.BAD_REQUEST_400, dataToJson(new ApiError(HttpStatus.BAD_REQUEST_400, "400 Bad Request")));
		else 
			return processImpl(value, urlParams);
	}

	public abstract Answer processImpl(V value, Map<String, String> urlParams);

	@Override
	public Object handle(Request request, Response response) throws Exception {
		Gson objectMapper = new Gson();
		V value = objectMapper.fromJson(request.body(), valueClass);
		Map<String, String> urlParams = request.params();
		Answer answer = process(value, urlParams);
		response.status(answer.getCode());
		response.type("application/json");
		response.body(answer.getBody());
		return answer.getBody();
	}

}

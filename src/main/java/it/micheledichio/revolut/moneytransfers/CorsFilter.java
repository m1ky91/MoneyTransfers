package it.micheledichio.revolut.moneytransfers;

import org.eclipse.jetty.http.HttpStatus;

import spark.Filter;
import spark.Request;
import spark.Response;

public class CorsFilter implements Filter {
	private static final String headerAllowHeaders = "Access-Control-Allow-Headers";
	private static final String headerAllowOrigin = "Access-Control-Allow-Origin";
	private static final String headerRequestMethod = "Access-Control-Request-Method";
	private static final String headerRequestMaxAge = "Access-Control-Max-Age";
	private static final String wildcard = "*";
	private static final String methods = "GET, PUT, POST, DELETE, OPTIONS";
	private static final String headers = "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range";

	public CorsFilter() {
	}

	@Override
	public void handle(Request request, Response response) throws Exception {
		response.header(headerAllowOrigin, wildcard);
		response.header(headerRequestMethod, methods);
		response.header(headerAllowHeaders, headers);
		
		if (request.requestMethod().equals("OPTIONS")) {
			response.header(headerRequestMaxAge, "1728000");
			response.status(HttpStatus.NO_CONTENT_204);
		}
	}
}
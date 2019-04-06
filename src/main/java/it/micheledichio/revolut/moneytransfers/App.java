package it.micheledichio.revolut.moneytransfers;

import static spark.Spark.*;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import it.micheledichio.revolut.moneytransfers.model.ApiError;

@SwaggerDefinition(host = "localhost:4567", //
info = @Info(description = "Money transfers between account", //
version = "V1", //
title = "MoneyTransfers", //
contact = @Contact(name = "Michele Di Chio", email = "micheledichio@gmail.com") ) , //
schemes = { SwaggerDefinition.Scheme.HTTP }, //
consumes = { "application/json" }, //
produces = { "application/json" })
public class App {

	public static final String APP_PACKAGE = "it.micheledichio.revolut.moneytransfers";

	public static void main(String[] args) {

		try {
			staticFileLocation("/doc");
			
			before(new CorsFilter());
			new OptionsController();

			// Scan classes with @Api annotation and add as routes
			RouteBuilder.setupRoutes(APP_PACKAGE);
			
			notFound((req, res) -> {
				Gson objectMapper = new Gson();
			    res.type("application/json");
			    return objectMapper.toJson(new ApiError(HttpStatus.NOT_FOUND_404, "404 Not found"));
			});

			// Build swagger json description
			final String swaggerJson = SwaggerParser.getSwaggerJson(APP_PACKAGE);
			
			get("/swagger", (req, res) -> {
				res.type("application/json");
				return swaggerJson;
			});

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}

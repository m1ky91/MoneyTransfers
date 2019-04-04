package it.micheledichio.revolut.moneytransfers;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

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
			
			// Quite unsafe!
			before(new CorsFilter());
			new OptionsController();

			// Scan classes with @Api annotation and add as routes
			RouteBuilder.setupRoutes(APP_PACKAGE);

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

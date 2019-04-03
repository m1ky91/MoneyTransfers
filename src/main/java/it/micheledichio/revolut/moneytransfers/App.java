package it.micheledichio.revolut.moneytransfers;

import static spark.Spark.before;
import static spark.Spark.get;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(host = "localhost:4567", //
info = @Info(description = "Money Transfers API", //
version = "V0.0.1", //
title = "Money transfers between bank account", //
contact = @Contact(name = "Michele Di Chio", email = "micheledichio@gmail.com") ) , //
schemes = { SwaggerDefinition.Scheme.HTTP }, //
consumes = { "application/json" }, //
produces = { "application/json" }, //
tags = { @Tag(name = "swagger") })
public class App {

	public static final String APP_PACKAGE = "it.micheledichio.revolut.moneytransfers";

	public static void main(String[] args) {

		try {
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

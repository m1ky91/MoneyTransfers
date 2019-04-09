package it.micheledichio.revolut.moneytransfers.test;

import java.util.LinkedList;
import java.util.List;

public class PathAvailableUtil {
	
	private List<String> paths = new LinkedList<String>();
	
	public PathAvailableUtil() {
		paths.add("GET /countries");
		paths.add("GET /transfers");
		paths.add("POST /accounts");
		paths.add("PUT /accounts");
		paths.add("GET /countries/:code");
		paths.add("GET /transfers/:id");
		paths.add("GET /rates/:id");
		paths.add("GET /transfers/sender/:username");
		paths.add("GET /accounts");
		paths.add("GET /rates");
		paths.add("PUT /users");
		paths.add("GET /currencies");
		paths.add("POST /users");
		paths.add("GET /accounts/:number");
		paths.add("POST /transfers");
		paths.add("GET /users");
		paths.add("GET /users/:username");
		paths.add("GET /currencies/:code");
	}

	public List<String> getPaths() {
		return paths;
	}

}

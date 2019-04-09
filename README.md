# MoneyTransfers

RESTful API for money transfers between accounts

## Getting Started

This project contains the implementation of a RESTful Web API to simulate the management of transfers between bank accounts. For this purpose has been used the Java framework [Spark](http://sparkjava.com/) and [Swagger core/UI](https://swagger.io/tools/swagger-ui/) for the documentation.

### Installing

To take the compiled code and package it in its distributable format, in this case a JAR:

```
mvn package
```

## Running the tests

To run only unit tests:

```
mvn test
```

## Running the application

To run the application from command line:

```
java -jar target/MoneyTransfer-jar-with-dependencies.jar
```

### Documentation and how to try the RESTful API

After we launched the application, we can see the documentation and try out the API at [http://localhost:4567/](http://localhost:4567/) or download the JSON Swagger definition from [http://localhost:4567/swagger](http://localhost:4567/swagger) in order to build a client fastly with [Swagger Codegen](https://swagger.io/tools/swagger-codegen/). 

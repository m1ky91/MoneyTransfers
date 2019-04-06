package it.micheledichio.revolut.moneytransfers.route;

import java.util.Map;

import it.micheledichio.revolut.moneytransfers.model.Validable;

public interface RequestHandler<V extends Validable> {

    Answer process(V value, Map<String, String> urlParams);

}
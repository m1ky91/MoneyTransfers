package it.micheledichio.moneytransfers.route;

import it.micheledichio.moneytransfers.model.Validable;

import java.util.Map;

public interface RequestHandler<V extends Validable> {

    Answer process(V value, Map<String, String> urlParams);

}
package it.micheledichio.revolut.moneytransfers.util;

public class HandleParam {
	
	public Long parseLongWithDefault(String param, Long number) {
		try {
			return Long.parseLong(param);
		} catch (NumberFormatException e) {
			return number;
		}
	}

}

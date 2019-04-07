package it.micheledichio.revolut.moneytransfers.test.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.micheledichio.revolut.moneytransfers.util.HandleParam;

public class HandleParamTest {
	
	@Test
	public void aNotLongParamHandledCorrectly() {		
		HandleParam handleParam = new HandleParam();

		assertEquals(Long.decode("-1"), handleParam.parseLongWithDefault("ababc", Long.parseLong("-1")));
	}
	
	@Test
	public void anOutOfRangeLongParamHandledCorrectly() {		
		HandleParam handleParam = new HandleParam();

		assertEquals(Long.decode("-1"), handleParam.parseLongWithDefault(String.valueOf("92233720368547758071"), Long.parseLong("-1")));
	}

}

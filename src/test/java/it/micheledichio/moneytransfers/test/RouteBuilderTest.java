package it.micheledichio.moneytransfers.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import it.micheledichio.moneytransfers.RouteBuilder;

public class RouteBuilderTest {
	
	@Test
	public void isBeingCreatedOnlyPathAvailable() {		
		PathAvailableUtil pathAvailableObj = new PathAvailableUtil();
		
		List<String> pathAvailable = pathAvailableObj.getPaths();
		List<String> pathCreated = new LinkedList<String>();
		try {
			pathCreated = RouteBuilder.setupRoutes("it.micheledichio.moneytransfers");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		Collections.sort(pathAvailable); 
		Collections.sort(pathCreated); 
		assertThat(pathAvailable, is(pathCreated));
	}

}

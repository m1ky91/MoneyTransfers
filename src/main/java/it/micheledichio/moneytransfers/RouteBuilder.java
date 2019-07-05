package it.micheledichio.moneytransfers;

import io.swagger.annotations.Api;
import org.reflections.Reflections;
import spark.Route;

import javax.ws.rs.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static spark.Spark.*;

public class RouteBuilder {

	public static List<String> setupRoutes(String packageName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<String> pathsList = new LinkedList<String>();
		Reflections reflections = new Reflections(packageName);
		Set<Class<?>> apiRoutes = reflections.getTypesAnnotatedWith(Api.class);

		for (Class<?> clazz : apiRoutes) {
			Route sparkRoute = (Route) clazz.getDeclaredConstructor().newInstance();
			Path path = clazz.getAnnotation(Path.class);
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				POST post = method.getAnnotation(POST.class);
				String friendlyRoute = path.value().replaceAll("\\{(.*?)\\}", ":$1"); // replace {param} with :param for Spark route pattern
				if (post != null) {
					post(friendlyRoute, sparkRoute);
					pathsList.add("POST " + friendlyRoute);
					break;
				}

				GET get = method.getAnnotation(GET.class);
				if (get != null) {
					get(friendlyRoute, sparkRoute);
					pathsList.add("GET " + friendlyRoute);
					break;
				}

				DELETE delete = method.getAnnotation(DELETE.class);
				if (delete != null) {
					delete(friendlyRoute, sparkRoute);
					pathsList.add("DELETE " + friendlyRoute);
					break;
				}

				PUT put = method.getAnnotation(PUT.class);
				if (put != null) {
					put(friendlyRoute, sparkRoute);
					pathsList.add("PUT " +friendlyRoute);
					break;
				}
			}

		}
		
		return pathsList;
		
	}

}

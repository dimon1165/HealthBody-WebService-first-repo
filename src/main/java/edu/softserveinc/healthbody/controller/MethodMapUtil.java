package edu.softserveinc.healthbody.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Request;

public class MethodMapUtil {

	private static Map<String, Pair<Method, Class<?>>> methodsClasUrlMapping = null;
	private static MethodMapUtil methodMapUtil = null;

	public static MethodMapUtil getInstanse() {
		if (methodMapUtil == null) {
			synchronized (MethodMapUtil.class) {
				if (methodMapUtil == null) {
					methodMapUtil = new MethodMapUtil();
				}
			}
		}
		return methodMapUtil;
	}

	private MethodMapUtil() {

		Reflections reflections = new Reflections("edu.softserveinc.healthbody.controller");

		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
		methodsClasUrlMapping = new HashMap<>();
		for (Class<?> annotatedClass : classes) {
			for (Method method : annotatedClass.getDeclaredMethods()) {
				methodsClasUrlMapping.put(method.getAnnotation(Request.class).url().toLowerCase(),
						new Pair<Method, Class<?>>(method, annotatedClass));
			}
		}
	}

	public Map<String, Pair<Method, Class<?>>> getMethodsClasUrlMapping() {
		return methodsClasUrlMapping;
	}
}

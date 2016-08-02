package edu.softserveinc.healthbody.listener;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.softserveinc.healthbody.annotation.Param;
import edu.softserveinc.healthbody.controller.MethodMapUtil;
import edu.softserveinc.healthbody.controller.Pair;
import edu.softserveinc.healthbody.controller.ParamUtils;
import edu.softserveinc.healthbody.controller.RequestParamUtils;
import edu.softserveinc.healthbody.log.Log4jWrapper;

/**
 * Servlet implementation class ListenerServlet
 */
@WebServlet("/listener")
public class ListenerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListenerServlet() {
		super();
		this.gson = new Gson();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getPathInfo();
		Pair<Method, Class<?>> methodClassPair = MethodMapUtil.getInstanse().getMethodsClasUrlMapping()
				.get(path.toLowerCase());

		try {
			/*
			 * Method method = methodClassPair.getL(); List<Object> parameters =
			 * new ArrayList<>(); Class<?>[] types = method.getParameterTypes();
			 * int i = 0; for (Annotation[] annotations :
			 * method.getParameterAnnotations()) {
			 * 
			 * for (Annotation annotation : annotations) { Param param = (Param)
			 * annotation; parameters.add(RequestParamUtils.toObject(types[i],
			 * request.getParameter(param.name()))); i++; }
			 * 
			 * }
			 */
			List<Object> parameters = getParameters(methodClassPair, request);
			wrightResponse(methodClassPair.getL().invoke(methodClassPair.getR().newInstance(), parameters.toArray()), response);
			ParamUtils.getLogin(request);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			Log4jWrapper.get().error("Could't load data: " , e);
		}

	}

	private List<Object> getParameters(Pair<Method, Class<?>> methodClassPair, HttpServletRequest request) {
		Method method = methodClassPair.getL();
		List<Object> parameters = new ArrayList<>();
		Class<?>[] types = method.getParameterTypes();
		int i = 0;
		for (Annotation[] annotations : method.getParameterAnnotations()) {

			for (Annotation annotation : annotations) {
				Param param = (Param) annotation;
				parameters.add(RequestParamUtils.toObject(types[i], request.getParameter(param.name())));
				i++;
			}

		}
		return parameters;
	}

	private void wrightResponse(Object object, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(object));
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

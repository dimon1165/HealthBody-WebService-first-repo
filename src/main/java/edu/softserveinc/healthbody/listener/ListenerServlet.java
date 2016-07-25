package edu.softserveinc.healthbody.listener;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.softserveinc.healthbody.controller.MethodMapUtil;
import edu.softserveinc.healthbody.controller.Pair;
import edu.softserveinc.healthbody.log.Log4jWrapper;

/**
 * Servlet implementation class ListenerServlet
 */
@WebServlet("/listener")
public class ListenerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListenerServlet() {
		super();
		this.gson = new Gson();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getPathInfo();
		Pair<Method, Class<?>> methodClassPair = MethodMapUtil.getInstanse().getMethodsClasUrlMapping().get(path.toLowerCase());

		try {
			wrightResponse(methodClassPair.getL().invoke(methodClassPair.getR()
					.newInstance()/* , request, response */), response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			Log4jWrapper.get().error("Could't load data");
		}

		/*
		 * if (path.equals("/c")) {
		 * 
		 * CompetitionCntr competitionCntr = new CompetitionCntr();
		 * response.setContentType("application/json"); PrintWriter out =
		 * response.getWriter();
		 * out.print(gson.toJson(competitionCntr.getAllComp(request,
		 * response))); out.flush();
		 * 
		 * }
		 */
		/*
		 * System.out.println(methodClassPair.getL().invoke(methodClassPair.getR
		 * () .newInstance() , request, response ));
		 */
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

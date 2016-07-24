package edu.softserveinc.healthbody.listener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import edu.softserveinc.healthbody.controller.CompetitionCntr;

/**
 * Servlet implementation class ListenerServlet
 */
@WebServlet("/ListenerServlet")
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
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		String path = request.getPathInfo();

		if (path.equals("/c")) {

			CompetitionCntr competitionCntr = new CompetitionCntr();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(competitionCntr.getAllComp(request, response)));
			out.flush();

		}
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

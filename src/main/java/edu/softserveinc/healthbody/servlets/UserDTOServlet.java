package edu.softserveinc.healthbody.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.softserveinc.healthbody.webservice.HealthBodyServiceImpl;

@WebServlet("/UserDTOServlet")
public class UserDTOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDTOServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HealthBodyServiceImpl healthBody = new HealthBodyServiceImpl();
		String UserLogin = request.getParameter("login");
		if(UserLogin != null){
			request.setAttribute("data", healthBody.getUserByLogin(UserLogin));
			getServletContext().getRequestDispatcher("/WEB-INF/views/UserDTOView.jsp").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/WEB-INF/views/UserDTOViewInit.jsp").forward(request, response);
		}	
	}
}
package edu.softserveinc.healthbody.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.softserveinc.healthbody.webservice.HealthBodyServiceImpl;

@WebServlet("/AllUsersServlet")
public class AllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AllUsersServlet() {
        super();
    }
    
    @Override
    protected final void doGet(final HttpServletRequest request, final HttpServletResponse response)
    		throws ServletException, IOException {
		HealthBodyServiceImpl healthBodyServiceImpl = new HealthBodyServiceImpl();
		String partNumber = request.getParameter("partNumber");
		String partSize = request.getParameter("partSize");
		if ((partNumber != null) && (partSize != null)) {
			request.setAttribute("GetAllUsers", 
					healthBodyServiceImpl.getAllUsers(Integer.parseInt(partNumber), Integer.parseInt(partSize)));
			getServletContext().getRequestDispatcher("/WEB-INF/views/AllUsersView.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/views/AllUsersViewInitial.jsp").forward(request, response);
	     }
	}
}

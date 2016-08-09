package edu.softserveinc.healthbody.servlets;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;

/**
	 * UserView Servlet takes data from userviewinitilapage.jsp 
	 * and with help of Jaxb technology transfer data form Object 
	 * into XML format.
	 * Return data in xml file 
	 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_VIEW_PAGE = "/WEB-INF/views/userview.jsp";
    private static final String USER_VIEW_INITIAL_PAGE = "/WEB-INF/views/userviewinitialpage.jsp";
      
   /**
	* Default constructor of UserServlet
	*/
    public UserServlet() {
    	}
    
	@Override
    protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		
		/** Get  User's login as parameter from jsp*/
		String userLogin = request.getParameter("login");
		
		if (userLogin != null) {
			try{
				/** Get UserDTO*/
				UserDTO userDTO = UserProfileServiceImpl.getInstance().get(userLogin);
			    	
			    	/**Create JAXBElement of type UserDTO and pass it the userDTO object */ 
			    JAXBElement<UserDTO> jaxbElement =  new JAXBElement<UserDTO>(new QName(UserDTO.class.getName()), UserDTO.class, userDTO);
			    
			    /**Create a String writer object for writing JAXBElement xml to string */ 
			    StringWriter writer = new StringWriter();
			    
			    /**JAXBContext for updating writer*/
			    JAXBContext context = JAXBContext.newInstance(UserDTO.class);
			    			    
			    /** Converting JAXBElement containing userDTO to xml format*/
			    context.createMarshaller().marshal(jaxbElement, writer);
		
			    
			    /** Print xml representation of userDTO object into console*/
		 	    Log4jWrapper.get().info(writer.toString());
		 	    
		 	   /** Transfer xml representation of userDTO object into jsp page*/
				request.setAttribute("data", writer);
				getServletContext().getRequestDispatcher(USER_VIEW_PAGE).forward(request, response);

			}
			catch (JAXBException | SQLException | JDBCDriverException | TransactionException e) {
				e.printStackTrace();
			}
			      } 
	    else {
			getServletContext().getRequestDispatcher(USER_VIEW_INITIAL_PAGE).forward(request, response);
		}	
	}
}

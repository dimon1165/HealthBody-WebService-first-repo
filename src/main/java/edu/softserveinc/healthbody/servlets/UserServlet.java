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
	private static final String USER_VIEW_RESULT_PAGE = "/WEB-INF/views/userview.jsp";
    private static final String USER_VIEW_LOGIN_PAGE = "/WEB-INF/views/userviewinitialpage.jsp";
    private static final String USER_BAD_REQUEST_PAGE = "/WEB-INF/views/userbadrequestpage.jsp";
      
   /**
	* Default constructor of UserServlet
	*/
    public UserServlet() {
    	}
    
	@Override
    protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		
		/** Get  User's login as parameter from jsp*/
		String userLogin = request.getParameter("login");
		
		/** Check if requested info from jsp is not null*/
		if (userLogin != null) {
			try{
				/** Get User by his login*/
				UserDTO userDTO = UserProfileServiceImpl.getInstance().get(userLogin);
					
					/** Check if user with such login exist's*/
					if (userDTO == null) {
						getServletContext().getRequestDispatcher(USER_BAD_REQUEST_PAGE).forward(request, response);
					}
			    		else {
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
							getServletContext().getRequestDispatcher(USER_VIEW_RESULT_PAGE).forward(request, response);

						}
			 	}
						catch (JAXBException | SQLException | JDBCDriverException | TransactionException e) {
							e.printStackTrace();
						}
		}
		
		/** If requested info is null */
	    else {
			getServletContext().getRequestDispatcher(USER_VIEW_LOGIN_PAGE).forward(request, response);
		}	
	}
}

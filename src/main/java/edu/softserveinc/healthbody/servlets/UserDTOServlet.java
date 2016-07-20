package edu.softserveinc.healthbody.servlets;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

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
			request.setAttribute("data", (healthBody.getUserByLogin(UserLogin)));
			getServletContext().getRequestDispatcher("/WEB-INF/views/UserDTOView.jsp").forward(request, response);
		     UserDTO userdto = new UserDTO();
		      userdto.setAge(healthBody.getUserByLogin(UserLogin).getAge().toString());
		      userdto.setEmail(healthBody.getUserByLogin(UserLogin).getEmail());
		      userdto.setFirstname(healthBody.getUserByLogin(UserLogin).getFirstname());
		      userdto.setGender(healthBody.getUserByLogin(UserLogin).getGender());
		      userdto.setGoogleApi(healthBody.getUserByLogin(UserLogin).getGoogleApi());
		      userdto.setHealth(healthBody.getUserByLogin(UserLogin).getHealth());
		      userdto.setIsDisabled(healthBody.getUserByLogin(UserLogin).getIsDisabled());
		      userdto.setLastname(healthBody.getUserByLogin(UserLogin).getLastname());
		      userdto.setLogin(healthBody.getUserByLogin(UserLogin)	.getLogin());
		      userdto.setPassword(healthBody.getUserByLogin(UserLogin).getPassword());
		      userdto.setPhotoURL(healthBody.getUserByLogin(UserLogin).getPhotoURL());
		      userdto.setRoleName(healthBody.getUserByLogin(UserLogin).getRoleName());
		      userdto.setScore(healthBody.getUserByLogin(UserLogin).getScore());
		      userdto.setStatus(healthBody.getUserByLogin(UserLogin).getStatus());
		      userdto.setWeight(healthBody.getUserByLogin(UserLogin).getWeight().toString());
		      userdto.setGroups(healthBody.getUserByLogin(UserLogin).getGroups());	
		      try {
		    	  	 File file = new File("/Users/Daniel/Desktop/File.xml");
		         JAXBElement<UserDTO> jaxbElement =  new JAXBElement<UserDTO>(new QName(UserDTO.class.getName()), UserDTO.class, userdto);
		         StringWriter writer = new StringWriter();
		         JAXBContext context = JAXBContext.newInstance(UserDTO.class);
		         context.createMarshaller().setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		         context.createMarshaller().marshal(jaxbElement, file);
		         System.out.println(writer.toString());
		      } catch (JAXBException e) {			
		         e.printStackTrace(); 
		      } 
		}else{
			getServletContext().getRequestDispatcher("/WEB-INF/views/UserDTOViewInit.jsp").forward(request, response);
		}	
	}
}
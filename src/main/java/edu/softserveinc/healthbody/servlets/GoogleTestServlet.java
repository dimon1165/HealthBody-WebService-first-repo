package edu.softserveinc.healthbody.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoogleTestServlet.
 */
@WebServlet("/gt")
public class GoogleTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GoogleTestServlet() {
		super();
	}

	@Override
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String rn = System.lineSeparator();
		out.print("<html lang=\"en\">" + rn);
		out.print("<head>" + rn);
		out.print("<meta name=\"google-signin-scope\" content=\"profile email\">" + rn);
		out.print(
				"<meta name=\"google-signin-client_id\" content=\"48524677967-juniqolaio06efre3m3q7774097q50u8.apps.googleusercontent.com\">"
						+ rn);
		out.print("<script src=\"https://apis.google.com/js/platform.js\" async defer></script>" + rn);
		out.print("</head>" + rn);
		out.print("<body>" + rn);
		out.print("<div class=\"g-signin2\" data-onsuccess=\"onSignIn\" data-theme=\"dark\"></div>" + rn);
		out.print("<script>" + rn);
		out.print("function onSignIn(googleUser) {" + rn);
		out.print("// Useful data for your client-side scripts:" + rn);
		out.print("var profile = googleUser.getBasicProfile();" + rn);
		out.print("console.log(\"ID: \" + profile.getId()); // Don't send this directly to your server!" + rn);
		out.print("console.log('Full Name: ' + profile.getName());" + rn);
		out.print("console.log('Given Name: ' + profile.getGivenName());" + rn);
		out.print("console.log('Family Name: ' + profile.getFamilyName());" + rn);
		out.print("console.log(\"Image URL: \" + profile.getImageUrl());" + rn);
		out.print("console.log(\"Email: \" + profile.getEmail());" + rn);
		out.print("// The ID token you need to pass to your backend:" + rn);
		out.print("var id_token = googleUser.getAuthResponse().id_token;" + rn);
		out.print("console.log(\"ID Token: \" + id_token);" + rn);
		out.print("console.log(\"JSON: \" + JSON.stringify(googleUser));" + rn);
		out.print("};" + rn);
		out.print("</script>" + rn);
		out.print("</body>" + rn);
		out.print("</html>" + rn);
	}

	@Override
	protected final void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
		}

		System.out.println("DO POST was here");
		System.out.println(sb.toString());
	}

}

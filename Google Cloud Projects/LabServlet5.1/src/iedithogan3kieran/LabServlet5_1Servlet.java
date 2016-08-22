package iedithogan3kieran;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class LabServlet5_1Servlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException
	{
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		UserService userService = UserServiceFactory.getUserService(); //means the user has to log in and out
		Principal myPrincipal = req.getUserPrincipal(); //allows the use of a logon, part of security
		String emailAddress = null;
		String thisURL = req.getRequestURI();	//retrieves thisURL
		String loginURL = userService.createLoginURL(thisURL); //url for login
		String logoutURL = userService.createLogoutURL(thisURL); //url for logout
		
		resp.setContentType("text/html"); //specifies file type to html
		if(myPrincipal == null) 
		{
			resp.getWriter().println("<p>You are Not Logged In time</p>"); //print statement for not logged in
			resp.getWriter().println("<p>You can <a href=\""+loginURL+"\">sign in here</a>.</p>"); //print statement for sign in
		} // end if not logged in
		if(myPrincipal !=null) 
		{
			emailAddress = myPrincipal.getName();
			resp.getWriter().println("<p>You are Logged in as (email): "+emailAddress+"</p>"); //print statement for logged in
			resp.getWriter().println("<p>You can <a href=\"" + logoutURL +"\">sign out</a>.</p>"); //print statement for signing out
		} // end if logged in
	}
}

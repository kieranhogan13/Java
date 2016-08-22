package iedithogan3kieran;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MultiplicationServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, Multiplication Servlet");
		resp.getWriter().println("Please enter ' ?A=_&B=_ ' after multiplication to calculate");
		String AString, BString;
		int Result;
		AString = req.getParameter("A");
		BString = req.getParameter("B");
		if(AString == null && BString == null)
		{
			AString = getServletConfig().getInitParameter("A");
			BString = getServletConfig().getInitParameter("B");
		}
		
		if (Verified(AString, BString))
		{
			Result = Integer.parseInt(AString) * Integer.parseInt(BString);
			resp.getWriter().println("A= " + AString + " B= " + BString);
			resp.getWriter().println("Result: " + Result);
		}
		else
		{
			resp.getWriter().println("Error from user input, please try again.");
		}
	}
	public boolean Verified(String AString, String BString)
	{
		try
		{			
			int Result = Integer.parseInt(AString) * Integer.parseInt(BString);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		catch(ArithmeticException e)
		{
			return false;
		}
		return true;
	}
}
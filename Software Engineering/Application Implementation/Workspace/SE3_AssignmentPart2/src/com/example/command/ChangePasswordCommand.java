package com.example.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.business.User;
import com.example.service.UserService;

public class ChangePasswordCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse)
	{
			
		UserService userService = new UserService();
		String forwardToJsp = "loginSuccess.jsp";		

		String password = request.getParameter("newpassword");
		String username = request.getParameter("username");
		
		if (password != null && username != null)
		{
			//Make call to the 'Model' using the UserServive class...
			User userChangePassword = userService.changePassword(password, username);
			if (userChangePassword != null)
			{
				forwardToJsp = "passwordChangeSuccess.jsp";	
				return forwardToJsp;
			}
		}
		return forwardToJsp;
	}
}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>

<body>
	Servlet forwarded to me... changePassword.jsp
	<br/>
    <form action="FrontController" method="post">
       <table>
           <tr>
               <td> Enter New Password  : </td><td> <input name="newpassword" size=15 type="password" /> </td> 
           </tr>
           <tr>
               <td> Enter Acc No for verification : </td><td> <input name="username" size=15 type="text" /> </td> 
           </tr>
       </table>
       <input type="hidden" name="action" value="ChangedPasswordResult" />
       <input type="submit" value="Change Password" />
   </form>
	<br/>	
	<a href="login.html">Return to Login page...</a>
</body>

</html>
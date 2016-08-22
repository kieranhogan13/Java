<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>PictureBox</title>
  	</head>
  	<body>
		<form action="/delete" method="post">
		<p>Your pictures:</p>
		<ul>
	  		<c:forEach var="i" items="${pictures}">
	    	<li>
	      		<a href="/serve?key=${i.key}"><img src="/serve?key=${i.key}" height="100" width="100"></a>
	      		<input type="checkbox" name="delete" value="${i.key}" />
	    	</li>
	  		</c:forEach>
		</ul>
		<input type="submit" value="Delete" />
		</form>
        <form action="${uploadURL}" method="post" enctype="multipart/form-data">
          <input type="file" name="picture" multiple="true"/><br /><br />
          <input type="submit" value="Upload Private Picture" />
        </form>
  </body>
</html>
package ie.dit.student.hogan3.kieran;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.*;

@SuppressWarnings("serial")
public class LoginMain extends HttpServlet 
{
	//THE MAIN SERVLET CLASS OF THE APP
	//REF: https://cloud.google.com/appengine/docs/java/javadoc/overview-summary
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
    {
    	//REF: http://goo.gl/7qSYow
        UserService userService = UserServiceFactory.getUserService(); //Creates userService instance
        User user = userService.getCurrentUser(); //obtains current users userService   
        //REF: http://goo.gl/hAVgkg
        Principal myPrincipal = req.getUserPrincipal(); //provides security for user, allows specification of admin
        //REF: http://goo.gl/Bb4yDm
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); //Creates blobStoreService instance
        String uploadUrl = blobstoreService.createUploadUrl("/upload"); //url for posting upload
        //REF: http://goo.gl/mCtaEA
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService(); //Creates datastoreService instance
        BlobInfoFactory blobInfoFactory = new BlobInfoFactory(); //Creates blobInfofactory instance
        //REF: http://goo.gl/ZR32MR
        List<Map<String, Object>> pictures = new ArrayList<Map<String, Object>>(); //List of picture maps
        
		String emailAddress = null;
        String loginURL = userService.createLoginURL("/"); //url for login
        String logoutURL = userService.createLogoutURL("/"); //url for logut
        
		if(myPrincipal == null) 
		{
			resp.getWriter().println("<p>You are Not Logged In</p>"); //Prints to screen
			resp.getWriter().println("<p>You are currently a guest</p>"); //Prints to screen
			resp.getWriter().println("<p>You can <a href=\""+loginURL+"\">sign in here</a>.</p>"); //Prints to screen
			String sampleURL = "http://www.lehigh.edu/google/google_search.png";
			resp.getWriter().println("<p><img src="+ sampleURL +"></p>"); //Prints to screen
		}
		else if(myPrincipal !=null) 
		{
			if(myPrincipal.getName().equals("mark.deegan@dit.ie") || myPrincipal.getName().equals("kieranhogan13@gmail.com"))
			{
		        if (userService.isUserAdmin() == true) //if isUserAdmin was ticked on admin login
		        {
					System.out.println("Admin verified");
		        }
				emailAddress = myPrincipal.getName(); //Gets user
				resp.getWriter().println("<p>You are Logged in as Admin (email): "+emailAddress+"</p>"); //Prints to screen
				resp.getWriter().println("<p>You can <a href=\"" + logoutURL +"\">sign out</a>.</p>"); //Prints to screen
				System.out.println("Logged in as Admin."); //Prints to screen
				
				//REF: http://goo.gl/9vfy89
				//REF: http://goo.gl/1uTkCT
		        Key key = KeyFactory.createKey("PhotoboxUser", user.getEmail()); //Creates key relative to user
		        //REF: https://cloud.google.com/appengine/docs/java/datastore/queries
		        Query q = new Query("picturekey").setAncestor(key);//Creates new query
				//REF: http://goo.gl/3gsRa9
		        PreparedQuery preparedQuery = datastoreService.prepare(q); //Fetches entity from query
		        //REF: http://goo.gl/oEYNOl
		        for (Entity result : preparedQuery.asIterable()) //Loops for all entities (pictures)
		        {
		        	//REF: http://goo.gl/ZR32MR
		            Map<String, Object> map = new HashMap<String, Object>(); //Creates new map for a picture
		            BlobKey blobKey = (BlobKey) result.getProperty("picture"); //Creates new blobKey
		            map.put("data", blobInfoFactory.loadBlobInfo(blobKey)); //Inserts blobKey data bytes, picture
		            map.put("key", KeyFactory.keyToString(result.getKey())); //Assigns the key to the blobKey
		            pictures.add(map); //Adds it to the pictures list
		        }
	        }    
		    else
			{
			    emailAddress = myPrincipal.getName(); //Gets user
				resp.getWriter().println("<p>You are Logged in as User (email): "+emailAddress+"</p>"); //Prints to screen
				resp.getWriter().println("<p>You can <a href=\"" + logoutURL +"\">sign out</a>.</p>"); //Prints to screen
				System.out.println("Logged in as normal user."); //Prints to screen
				
				//REF: http://goo.gl/9vfy89
				//REF: http://goo.gl/1uTkCT
				Key key = KeyFactory.createKey("PhotoboxUser", user.getEmail()); //Creates key instance relative to user
		        //REF: https://cloud.google.com/appengine/docs/java/datastore/queries
				Query q = new Query("picturekey").setAncestor(key); //Creates new query
				//REF: http://goo.gl/3gsRa9
		        PreparedQuery preparedQuery = datastoreService.prepare(q); //Fetches entity from query
		        //REF: https://cloud.google.com/appengine/docs/java/datastore/entities
		        for (Entity result : preparedQuery.asIterable()) //Loops for all entities (pictures)
		        {
		        	//REF: http://goo.gl/ZR32MR
		            Map<String, Object> map = new HashMap<String, Object>(); //Creates new map for a picture
		            BlobKey blobKey = (BlobKey) result.getProperty("picture"); //Creates new blobKey
		            map.put("data", blobInfoFactory.loadBlobInfo(blobKey)); //Inserts blobKey data bytes, picture
		            map.put("key", KeyFactory.keyToString(result.getKey())); //Assigns the key to the blobKey
		            pictures.add(map); //Adds it to the pictures list
		        }
			}
			//REF: http://goo.gl/RGV8Sc
	        req.setAttribute("uploadURL", uploadUrl); //Sends upload to jsp
	        req.setAttribute("pictures", pictures); //Sends list of pictures to jsp
	        RequestDispatcher jsp = req.getRequestDispatcher("index.jsp"); //Runs the jsp, works correctly locally
	        jsp.forward(req, resp);
		}

        resp.setContentType("text/html");
    }
}
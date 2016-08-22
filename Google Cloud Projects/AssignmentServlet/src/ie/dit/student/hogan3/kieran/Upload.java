package ie.dit.student.hogan3.kieran;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.*;

@SuppressWarnings("serial")
public class Upload extends HttpServlet 
{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
 	{
    	//REF: http://goo.gl/7qSYow
        UserService userService = UserServiceFactory.getUserService();  //Creates userService instance
        User user = userService.getCurrentUser(); //obtains current users userService
        //REF: http://goo.gl/Bb4yDm
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); //Creates blobStoreService instance
        //REF: http://goo.gl/mCtaEA
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService(); //Creates datastoreService instance
    	//REF: http://goo.gl/ZR32MR
        Map<String, List<BlobKey>> details = blobstoreService.getUploads(req); //Creates map for pictures details
        List<BlobKey> blobKeyList = details.get("picture"); //List for blobKeys of picture details
		
        if (blobKeyList == null) 
		{
			System.out.println("No picture selected."); 
			resp.sendRedirect("/logmain"); //Return to main servlet
		}
		else
		{
	        Key key = KeyFactory.createKey("PhotoboxUser", user.getEmail()); //Creates key instance relative to user
	        for (BlobKey blobKey : blobKeyList) //Loops for all blobKeys
	        {
	            Entity pictureUpload = new Entity("picturekey", key);//New picture upload entity
	            pictureUpload.setProperty("user", user); //Attach user to upload
	            pictureUpload.setProperty("picture", blobKey); //Attach picture data to upload
	            datastoreService.put(pictureUpload); //Adds to the datastore
	        }
			System.out.println("Uploaded the picture.");
	        resp.sendRedirect("/loginmain"); //Return to main servlet
		}
    }
}

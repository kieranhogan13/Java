package ie.dit.student.hogan3.kieran;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.datastore.*;

@SuppressWarnings("serial")
public class Delete extends HttpServlet 
{
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {
        //REF: http://goo.gl/mCtaEA
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService(); //Creates datastoreService instance
        String[] Keys = req.getParameterValues("delete"); //Retrieves list of deletes from jsp
        List<Key> deleteKeys = new ArrayList<Key>(); //List of pictures to delete
        //REF: http://goo.gl/gU6yJH
        if (Keys != null) 
        {
        	for (String Keyi : Keys) //For all keys
        	{
                try
                {
                    //REF: http://goo.gl/G25Och
                    Entity picturekey = datastoreService.get(KeyFactory.stringToKey(Keyi)); //Entity to delete
                    BlobKey blobKey = (BlobKey)picturekey.getProperty("picture"); //BlobKey for picture
                    Key blobInfoKey = KeyFactory.createKey(BlobInfoFactory.KIND, blobKey.getKeyString()); //BlobInfo to remove
                    deleteKeys.add(blobInfoKey); //adds to delete blobInfoKey
                    deleteKeys.add(picturekey.getKey()); //adds to delete pictureKey
                    System.out.println("Deleted the picture(s)");
                } 
                catch (EntityNotFoundException e) 
                {
                	
                }
            }
            datastoreService.delete(deleteKeys.toArray(new Key[0])); //Deletes the specified pictures
            resp.sendRedirect("/loginmain"); //Return to main servlet
        }
        else
        {
            resp.sendRedirect("/loginmain"); //Return to main servlet
        }
    }
}
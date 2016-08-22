package ie.dit.student.hogan3.kieran;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.datastore.*;

@SuppressWarnings("serial")
public class Serve extends HttpServlet 
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {
        //REF: http://goo.gl/Bb4yDm
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        //REF: http://goo.gl/mCtaEA
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        //REF: http://goo.gl/oEYNOl
        Entity data = null; //empty data
        //REF: http://goo.gl/KnhZ49
        BlobKey blobKey = null; //empty blobKey
        String key = req.getParameter("key"); //Gets key for picture from jsp
        
        if (key != null) //if key not empty
        {
            try 
            {
                data = datastoreService.get(KeyFactory.stringToKey(key)); //gets key for picture
                blobKey = (BlobKey)data.getProperty("picture"); //gets data for picture
            } 
            catch (EntityNotFoundException e) //required by datastoreService.get
            {

            }
        }
        
        if (blobKey != null) //if blobKey not empty
        {
            blobstoreService.serve(blobKey, blobstoreService.getByteRange(req), resp); //Returns the picture
            System.out.println("Opened the picture.");
        } 
    }
}

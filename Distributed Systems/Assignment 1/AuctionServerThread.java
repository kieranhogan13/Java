/*Kieran Hogan - C12561353 - DT228/4 - November 2015*/

import java.net.*;
import java.io.*;


public class AuctionServerThread extends Thread
{  	
	private AuctionServer    server    = null;
  	private Socket           socket    = null;
   	private int              ID        = -1;
   	private DataInputStream  streamIn  =  null;
   	private DataOutputStream streamOut = null;
   	private Thread 			thread;

   	public AuctionServerThread(AuctionServer _server, Socket _socket)
   	{
	  	super();
      	server = _server;
      	socket = _socket;
      	ID     = socket.getPort();
   	}

   	// Sends messages to the clients
   	public void send(String msg)
   	{
	   	try
	   	{
		    streamOut.writeUTF(msg);
         	streamOut.flush();
       	}
       	catch(IOException ioe)
       	{
		  	System.out.println(ID + " ERROR sending: " + ioe.getMessage());
          	server.remove(ID);
          	thread=null;
       	}
   	}
   	
   	public int getID()
   	{
	   	return ID;
   	}

   	// Runs the server thread
   	public void run()
   	{
	  	System.out.println("Server Thread " + ID + " running.");
	  	thread = new Thread(this);
      	while (true)
      	{
		 	try
		 	{
			server.broadcast(ID, streamIn.readUTF());

         	int pause = (int)(Math.random()*3000);
		 	Thread.sleep(pause);
			}
			catch (InterruptedException e)
			{
				System.out.println(e);
			}
	        catch(IOException ioe)
	        {
	        	// Had to comment this out in order to let people leave the auction freely
				// System.out.println(ID + " ERROR reading: " + ioe.getMessage());
	            server.remove(ID);
	            thread = null;
        	}
     	}
   	}

   	// Manages the data input and output streams
   	public void open() throws IOException
   	{
	  	streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
      	streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
   	}

   	// Close cases
   	public void close() throws IOException
   	{
	   	if (socket != null)
	   		socket.close();

      	if (streamIn != null)
      		streamIn.close();

      	if (streamOut != null)
      		streamOut.close();
   	}
}
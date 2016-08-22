/*Kieran Hogan - C12561353 - DT228/4 - November 2015*/

import java.net.*;
import java.io.*;


public class AuctionClientThread extends Thread
{  
	private Socket           socket   = null;
	private AuctionClient    client   = null;
	private DataInputStream  streamIn = null;

	// Auction client thread is started with client and socket parameters
	public AuctionClientThread(AuctionClient _client, Socket _socket)
	{  
		client   = _client;
		socket   = _socket;
		open();
		start();
	}

	// Creates input stream for messages, and catches exceptions
	public void open()
	{  
		try
		{
			// Creates input stream for messages
		  	streamIn  = new DataInputStream(socket.getInputStream());
		}
		catch(IOException ioe)
		{
		 	System.out.println("Error getting input stream: " + ioe);
			client.stop();
		}
	}

	// Closes the thread
	public void close()
	{  try
		{  
			if (streamIn != null) streamIn.close();
		}
		catch(IOException ioe)
		{  
			System.out.println("Error closing input stream: " + ioe);
		}
	}

	// Runs the thread using
	public void run()
	{
		while (true && client!= null)
		{
		  	try 
		  	{
		  		// Handles messages
				client.handle(streamIn.readUTF());
			}
			catch(IOException ioe)
			{
			  	client = null;
			  	System.out.println("Listening error: " + ioe.getMessage());
			}
		}
	}
}




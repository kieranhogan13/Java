/*Kieran Hogan - C12561353 - DT228/4 - November 2015*/

import java.net.*;
import java.io.*;

public class AuctionClient implements Runnable
{  
	private Socket socket              = null;
	private Thread thread              = null;
	private BufferedReader  console    = null;
	private DataOutputStream streamOut = null;
	private AuctionClientThread client = null;
	private String auctionName;

	// Auction client is started with client and socket and user name parameters from the command line
	public AuctionClient(String serverName, int serverPort, String name)
	{
	  	System.out.println("Establishing connection. Please wait ...");

	  	this.auctionName = name;
		try
		{
			// Creates new socket
		 	socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);
			System.out.println("Welcome "+ auctionName);
			start();
		}
		catch(UnknownHostException uhe)
		{
		  	System.out.println("Host unknown: " + uhe.getMessage());
	  	}
		catch(IOException ioe)
		{
		  	System.out.println("Unexpected exception: " + ioe.getMessage());
	  	}
	}

	// Checks user input to ensure its numeric
	public boolean isNumeric(String input) 
	{
		try 
		{
			Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException e) 
		{
			return false;
		}
	}

	// Sends messages to the auction server
	public void run()
	{
		while (thread != null){
		 try {
				String message = console.readLine();
				
				if (isNumeric(message))
				{
					//Sends the message
					streamOut.writeUTF(message);
					streamOut.flush();
				}
				else
				{
					System.out.println(auctionName + ", enter a valid amount for the bid!");
				}
			}
			catch(IOException ioe)
			{  
				System.out.println("Sending error: " + ioe.getMessage());
				stop();
			}
		}
	}

	// Handles the replies from the server
	public void handle(String msg)
	{  if (msg.equals(".exit"))
		{ 
			stop();
		}
		else
			System.out.println(msg);
	}

	// Creates new stream readers and buffers on launch
	public void start() throws IOException
	{
	  	console = new BufferedReader(new InputStreamReader(System.in));
		streamOut = new DataOutputStream(socket.getOutputStream());
		if (thread == null)
		{  
			// If there is no thread, it creates a thread for the Client
			client = new AuctionClientThread(this, socket);
			thread = new Thread(this);
			thread.start();
		}
	}

	// Closes client
	public void stop()
	{
		try
		{  
			if (console   != null)  console.close();
			if (streamOut != null)  streamOut.close();
			if (socket    != null)  socket.close();
		}
		catch(IOException ioe)
		{
		  System.out.println("Error closing ...");
		}
		client.close();
		thread = null;
	}

	// Main checks that user has provided correct command line arguments
	public static void main(String args[])
	{  
		// Clears the screen somewhat for cleaner UI
		for (int i=0; i<50; i++)
		{
			System.out.println();
		}
		AuctionClient client = null;
		if (args.length != 3)
			System.out.println("Usage: java AuctionClient host port name");
		else
			client = new AuctionClient(args[0], Integer.parseInt(args[1]), args[2]);
	}
}

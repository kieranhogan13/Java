/*Kieran Hogan - C12561353 - DT228/4 - November 2015*/

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.ListIterator;
import java.util.concurrent.*;
import java.util.Timer;
import java.util.TimerTask;

public class AuctionServer implements Runnable
{  
   
   // Array of clients  
   private AuctionServerThread 		clients[] = new AuctionServerThread[50];
   private ServerSocket 			server = null;
   private Thread       			thread = null;
   private int 						clientCount = 0;
   // List for storing objects
   public ArrayList<Item> 			items = new ArrayList<Item>();
   // Had to initialise my objects here globaly due to error
   public Item 						item1 = new Item("Lamp", 0, 300, 0);
   public Item 						item2 = new Item("Ring", 0, 500, 1);
   public Item 						item3 = new Item("Book", 0, 200, 2);
   public Item 						item4 = new Item("Vase", 0, 700, 3);
   public Item 						item5 = new Item("Coat", 0, 400, 4);
   // Used to store the item currently on auction
   public Item 						currentItem = item1;
   // Variable for timer time, and defalt used as value to reset to
   public int 						timeDefault = 9;
   public int 						time = timeDefault;

   // Aucton server is started with the port parameter only
   public AuctionServer(int port)
   {
   	 	try 
    	{
    		// Starts the auction with a UI
	     	System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server.getInetAddress());
			start();
			System.out.println("The auction has started. Here are the items:");
			// Add items to list
			items.add(item1);
			items.add(item2);
			items.add(item3);
			items.add(item4);
			items.add(item5);
			System.out.println("---------------------------------------------");
			for(Item ite: items)
			{
				System.out.println(ite);
			}
			// Start the timer
			timer();
			System.out.println("---------------------------------------------");
			System.out.println("Current " + currentItem);
	    }
    	catch(IOException ioe)
	    {
	      	System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
	    }
 	}

 	/* The timer method contains all of the logic
 	for dealing with the coutndown reaching 0. When
 	this happens, the various outcomes are managed */
   	public void timer()
   	{
   		// Creates timer
      	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
      	executor.scheduleAtFixedRate(new Runnable()
      	{
         	public void run() 
         	{
         		// When time is greater than 0 it displays
	            if (time >= 0)
	            {
	               System.out.print('\r');
	               System.out.print("Time: " + time--);
	            }
	            // When the timer hits 0
	            if (time < 0)
	            {
	               	System.out.print('\r');
	               	// If highest bid is less than reserve
	               	if (currentItem.currentBid<currentItem.reservePrice)
	               	{
	                  	if(currentItem.getIndex()+1<items.size())
	                  	{
	                  		// Next item becomes current item
	                     	currentItem = items.get(currentItem.getIndex()+1);
	                  	}
	                  	else if(items.size()==2)
	                  	{
	                     	if(currentItem.getIndex() == 0)
	                     	{
	                     		// Next item becomes current item
	                        	currentItem = items.get(currentItem.getIndex()+1);
	                     	}
	                    	else
	                    		// Item at 0 is made current
	                        	currentItem = items.get(0);
	                  	}
	                  	else
	                  	{
	                  		// Item at 0 is made current                    
	                        currentItem = items.get(0);
	                  	}
	                  	System.out.println("--- ITEM NOT SOLD! ---");
	                  	System.out.print('\r');
	                  	// Updated Items list printed out
	                  	System.out.println("---------------------------------------------");
	                  	for(Item ite: items)
	                  	{
	                     	System.out.println(ite);
	                  	}
	                  	System.out.println("---------------------------------------------");  
	                  	System.out.print('\r');
	                  	System.out.println("Current " + currentItem);
	                  	// Reset timer
	                  	time = timeDefault;
	                  	// Send update to all clients
	                  	for (int i = 0; i < clientCount; i++)
	                  	{
	                     	clients[i].send("--- This item did not sell, and will come back around! ---");
	                     	clients[i].send("Current " + currentItem  + " | Time left: " + time + " seconds");
	                     	clients[i].send("Please enter a bid: ");
	                  	}
	               	}
	               	// When the bid is greater than or equal to the reserve
	               	else if(currentItem.currentBid>=currentItem.reservePrice)
	               	{
						if(currentItem.getIndex()+1<items.size())
						{
							// Item is sold, removed, and next item becomes current
							items.remove(currentItem);
							currentItem = items.get(currentItem.getIndex());
							currentItem.index = items.indexOf(currentItem);
	                  	}
	                  	else
	                  	{
	                  		// Item is sold, removed, and next item becomes current
							items.remove(currentItem);
							currentItem = items.get(0);
							currentItem.index = items.indexOf(currentItem);   
						}
						// Update items list
	                  	for(Item item : items) 
	                  	{
	                     	item.index = items.indexOf(item);
	                  	}
	                  	System.out.println("--- ITEM SOLD! ---");
	                  	System.out.print('\r');
	                  	// Print out new items list
	                 	System.out.println("---------------------------------------------");
	                  	for(Item ite: items)
	                  	{
	                     	System.out.println(ite);
	                  	}
	                  	System.out.println("---------------------------------------------");
	                  	System.out.print('\r');
	                  	System.out.println("Current " + currentItem);
	                  	// Reset timer
	                  	time = timeDefault;
	                  	// Send update to all clients
	                  	for (int i = 0; i < clientCount; i++)
	                  	{ 
	                     	clients[i].send("--- ITEM SOLD! ---");
	                     	clients[i].send("Current " + currentItem  + " | Time left: " + time + " seconds");
	                     	clients[i].send("Please enter a bid:");
	                  	}
	               	}
	               	
	               	// For end of program, does not work
	               	if (items.size()==0)
	               	{
	               		for (int i = 0; i < clientCount; i++)
	                  	{
	               			clients[i].send("--- The auction has ended! Ctrl + C to exit! Goodbye... ---");	
	               		}
	               		System.exit(0);
	               	}
            	}
         	}	
      	}, 0, 1, TimeUnit.SECONDS);
   	}

   // Waits for clients and accepts them when they connect
   public void run()
   {
		while (thread != null)
		{
			try
			{
				System.out.println(" ----- Waiting for a bidder to join ... ----- ");
				addThread(server.accept());

				int pause = (int)(Math.random()*3000);
				Thread.sleep(pause);
        	}
        	catch(IOException ioe)
        	{
          		System.out.println("   ----- Server accept error: " + ioe);
         	 	stop();
        	}
        	catch (InterruptedException e)
        	{
          		System.out.println(e);
        	}
      	}
   	}

   	// Starts a thread
	public void start()
	{
      	if (thread == null) 
		{
			thread = new Thread(this);
			thread.start();
      	}
   	}

	public void stop()
	{
     	thread = null;
	}	

	// Method for finding client by ID
   	private int findClient(int ID)
   	{
		for (int i = 0; i < clientCount; i++)
 		if (clients[i].getID() == ID)
    	    return i;
      	return -1;
   	}

   	// Method for sending various messages to users on message reciept
	public synchronized void broadcast(int ID, String input)
	{
  		int newBid = Integer.parseInt(input);
      	boolean newHigh = false;
      	System.out.println(" ---- Bid received from bidder " + ID + ": $" + input + " ---- ");
      	// Logic for bid current high etc
      	if (newBid > currentItem.currentBid)
      	{
         	newHigh = true;
         	time = timeDefault;
      	}
      	for (int i = 0; i < clientCount; i++)
      	{
      		// Client ID is from all but the user, and there is a new high, send message to all but the user
         	if(clients[i].getID() != ID && newHigh)
         	{
            	currentItem.currentBid = newBid;
            	clients[i].send("Bidder "+ ID + " is the highest bidder with: $" + input + " | Reserve: $" + currentItem.reservePrice); // sends messages to clients
         		clients[i].send("Current " + currentItem + " | Time left: " + time + " seconds");
         	}
         	// Client ID is from the user, and there is a new high, send message to the user
         	if (clients[i].getID() == ID && newHigh) 
         	{
            	currentItem.currentBid = newBid;
            	clients[i].send("You are the new highest bidder!");
            	clients[i].send("Current " + currentItem + " | Time left: " + time + " seconds");
            	System.out.println("Bidder "+ ID + " is the highest bidder with: $" + input + " | Reserve: $" + currentItem.reservePrice);
         	} 
         	// Client ID is from user, and the bid is less than or equal to the current high
         	else if (clients[i].getID() == ID && !newHigh) 
         	{
            	clients[i].send("Bid is less than or equal to current highest bid. Enter a higher bid:");
            	clients[i].send("Current " + currentItem + " | Time left: " + time + " seconds");
         	}
      	}
      	// Wakes up all threads that are waiting on this object's monitor
      	notifyAll();
    }

    // Manages the removal of clients when they quit
   	public synchronized void remove(int ID)
   	{
    	int pos = findClient(ID);
      	if (pos >= 0)
      	{
         	AuctionServerThread toTerminate = clients[pos];
         	System.out.println(" --- Removing client thread " + ID + " at " + pos + " --- ");
         	if (pos < clientCount-1)
            	for (int i = pos+1; i < clientCount; i++)
               		clients[i-1] = clients[i];
         		clientCount--;
         		try
         		{
         			toTerminate.close();
         		}
         		catch(IOException ioe)
         		{
         			System.out.println("Error closing thread: " + ioe);
         		}
         		toTerminate = null;
         		// Resets the bid when a bidder leaves
            	currentItem.currentBid = 0;
         		time = timeDefault;
         		for (int i = 0; i < clientCount; i++)
            	{
            		clients[i].send("A bidder has left the auction, this bid is reset");
               		clients[i].send("Current " + currentItem  + " | Time left: " + time + " seconds");
               		clients[i].send("Please enter a bid: ");
            	}
         		System.out.println("----- Client " + pos + " has left -----");
         		System.out.println("----- The bid has been reset  -----");
         	// Wakes up all threads that are waiting on this object's monitor
         	notifyAll();
      	}
   	}

   	// Adds a thread whenever a new client enters
   	private void addThread(Socket socket)
   	{
      	if (clientCount < clients.length)
      	{
         	System.out.println(" ----- A new bidder has entered the auction ... -----");
         	//System.out.println(" Client accepted: " + socket);
         	clients[clientCount] = new AuctionServerThread(this, socket);
         	try
         	{
            	clients[clientCount].open();
            	clients[clientCount].start();
            	clientCount++;
            	for (int i = 0; i < clientCount; i++)
            	{
            		clients[i].send("A new bidder has joined the auction");
               		clients[i].send("Current " + currentItem  + " | Time left: " + time + " seconds");
               		clients[i].send("Please enter a bid: ");
            	}
         	}
         	catch(IOException ioe)
         	{
       			System.out.println(" Error opening thread: " + ioe);
         	}
      	}
      	else
         	System.out.println(" Client refused: maximum " + clients.length + " reached.");
   	}

   	// Manages command line calling of server, ensures correct parameters included
   	public static void main(String args[]) 
   	{
      	for (int i=0; i<50; i++)
      	{
         	System.out.println();
      	}
      	AuctionServer server = null;
      	if (args.length != 1)
         	System.out.println(" Usage: java AuctionServer port");
      	else
         	server = new AuctionServer(Integer.parseInt(args[0]));
   	}
}
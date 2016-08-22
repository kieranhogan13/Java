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
   private AuctionServerThread clients[] = new AuctionServerThread[50];
   private ServerSocket server = null;
   private Thread       thread = null;
   private int clientCount = 0;
   private final Timer timer = new Timer();
   private int currentItemBid = 100;
   public List<Item> items = new ArrayList<Item>();
   public Item item1 = new Item("Lamp", 0, 300, 0);
   public Item item2 = new Item("Ring", 0, 500, 1);
   public Item item3 = new Item("Book", 0, 200, 2);
   public Item item4 = new Item("Vase", 0, 700, 3);
   public Item item5 = new Item("Coat", 0, 400, 4);
   public Item currentItem = item1;
   public int time = 10;

   public AuctionServer(int port)
   {
    try {

     System.out.println("Binding to port " + port + ", please wait  ...");
         server = new ServerSocket(port);
         System.out.println("Server started: " + server.getInetAddress());
         start();
         System.out.println("The auction has started:");
         items.add(item1);
         items.add(item2);
         items.add(item3);
         items.add(item4);
         items.add(item5);
         //System.out.println("Items up for auction:");
         
         System.out.println("---------------------------------------------------");
         for(Item ite: items)
         {
            System.out.println(ite);
         }
         Timer();
         System.out.println("---------------------------------------------------");
         System.out.print("          | Current " + currentItem);
      }
      catch(IOException ioe)
      {
      System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());

      }
   }

   public void Timer()
   {
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
      executor.scheduleAtFixedRate(new Runnable()
      {
         public void run() 
         {
            if (time >= 0)
            {
               System.out.print('\r');
               System.out.print("Time: " + time--);
            }
            if (time < 0)
            {
               //executor.shutdown();
               System.out.print('\r');
               //System.out.print("   Times up!");
               
               if (currentItem.currentBid<currentItem.reservePrice)
               {
                  if(currentItem.getPosition()+1<items.size())
                  {
                     //currentItem.getPosition()+1
                     if(currentItem.isSold == false) 
                     {
                        currentItem = items.get(currentItem.getPosition()+1);
                     }
                     // else
                     // {
                     //    currentItem = items.get(currentItem.getPosition()+2);
                     // }
                  }
                  else
                  {
                     currentItem = items.get(0);
                     if(currentItem.isSold == true) 
                     {
                        currentItem = items.get(currentItem.getPosition()+1);
                     }
                  }
                  // for(Item item : items) 
                  // {
                  //    if(item.isSold == false)
                  //    {
                  //       if (!currentItem.itemName.equals(item.itemName))
                  //       {
                  //          currentItem = item;
                  //          System.out.println(currentItem.itemName);
                  //          break;
                  //       }
                  //    }     
                  // }
                  System.out.print('\r');
                  System.out.println("Not Sold!");
                  System.out.print('\r');
                  System.out.print("          | Current " + currentItem);
                  for (int i = 0; i < clientCount; i++)
                  {
                     clients[i].send("--- Last item did not sell! ---");
                     clients[i].send("| Current " + currentItem.toString());
                     clients[i].send("Please enter a bid: ");
                  }
                  time = 10;
               }

               if(currentItem.currentBid>=currentItem.reservePrice)
               {
                  currentItem.isSold = true;
                  if(currentItem.getPosition()+1<items.size())
                  {
                     //currentItem.getPosition()+1
                     if(currentItem.isSold == false) 
                     {
                        currentItem = items.get(currentItem.getPosition()+1);
                     }
                  }

                  System.out.print('\r');
                  System.out.print("Item Sold! ");
                  System.out.print('\r');
                  System.out.print("          | Current " + currentItem);
                  for(Item item : items) 
                  {
                     if(currentItem.itemName==item.itemName)
                     {
                        //items.remove(item);
                        item.isSold = true;                     
                     }
                  }
                  for(Item item : items) 
                  {
                     // if(item.isSold == true) 
                     // {
                     //    currentItem = items.get(currentItem.getPosition()+1); 
                     //    items.remove(item);
                     // }
                     if(item.isSold == false) 
                     {
                        currentItem = item;
                        System.out.print('\r');
                        time = 10;
                        break;
                     }
                  }

                  //System.out.print("          | Current " + currentItem);
                  //System.out.print('\r');
                  for (int i = 0; i < clientCount; i++)
                  {
                     clients[i].send("--- LAST ITEM SOLD! ---");
                     clients[i].send("| Current " + currentItem.toString());
                     clients[i].send("Please enter a bid:");
                  }
                  time = 10;
                  //time = 60;
               }

               
            }
         }
      }, 0, 1, TimeUnit.SECONDS);
   }


   public void run()
   {
    while (thread != null)
      {
        //System.out.println(items);
        //int currentBid1 = item1.getCurrentBid();
        try
        {
          System.out.println("              Waiting for a bidder to join ...");
          addThread(server.accept());

          int pause = (int)(Math.random()*3000);
          Thread.sleep(pause);
        }
        catch(IOException ioe)
        {
          System.out.println("              Server accept error: " + ioe);
          stop();
        }
        catch (InterruptedException e)
        {
          System.out.println(e);
        }
      }
   }

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

   private int findClient(int ID)
   {
      for (int i = 0; i < clientCount; i++)
         if (clients[i].getID() == ID)
            return i;
      return -1;
   }

   // public void timeMethod()
   // {
   //    if (clientCount > 1)
   //       {
   //          timer.scheduleAtFixedRate(new TimerTask() 
   //          {
   //             int i = 60;
   //             public void run() 
   //             {
   //                System.out.print('\r');
   //                System.out.print(i--);
   //                if (i< 0)
   //                   timer.cancel();
   //             }
   //          }, 0, 1000);
   //       }

   //       ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
   //       executor.scheduleAtFixedRate(new Runnable()
   //       {
   //          public void run() 
   //          {
   //                System.out.print('\r');
   //                System.out.print(i--);
   //                if (i< 0)
   //                   timer.cancel();
   //          }
   //       }, 0, 1, TimeUnit.SECONDS);
   // }

   // public synchronized void sendToClient(int ID, String input) 
   // {
   //    for (int i = 0; i < clientCount; i++)
   //    {
   //       clients[i].send("Item currently up for bidding is: " + currentItem.itemName);
   //    }
   // }

   public synchronized void broadcast(int ID, String input)
   {
      // for(Item item : items) 
      // {
      //    if(item.isSold == false) 
      //    {
      //       currentItem = item;
      //       if (time == 0)
      //       {
      //          time = 10;
      //          //currentItem = itemIterator.next();
      //          //StartTimer();
      //       }
      //       break;
      //    }
      // }
      int newBid = Integer.parseInt(input);

      //currentItemBid = item1.currentBid;
      // System.out.println("");
      // System.out.println(currentItemBid);
      // System.out.println("");
      boolean newHigh = false;
      System.out.println(" Accepted bid - Bidder " + ID + ": $" + input);
      if (newBid > currentItem.currentBid)
      {
         newHigh = true;
         time = 10;
      }

      for (int i = 0; i < clientCount; i++)
      {

         if(clients[i].getID() != ID && newHigh)
         {
            currentItem.currentBid = newBid;
            clients[i].send("Bidder "+ ID + " is the highest bidder with: $" + input + " | Reserve: $" + currentItem.reservePrice); // sends messages to clients
            //executor.shutdown();
            //StartTimer();
           //time = 60;
         }
         
         if (clients[i].getID() == ID && newHigh) 
         {
            currentItem.currentBid = newBid;
            clients[i].send("You are the new highest bidder!");
            clients[i].send("| Current " + currentItem);
         } 

         else if (clients[i].getID() == ID && !newHigh) 
         {
            clients[i].send("Bid is less than or equal to current highest bid. Enter a higher bid:");
         }

           // if (timer < 0)
           // {
           //     clients[i].send("Item "+ i + "starting at: $" + i[0]);
           // }
           //clients[i].send("Bidder "+ ID + ": $" + input);
      }
      notifyAll();
    }

   public synchronized void remove(int ID)
   {
    int pos = findClient(ID);
      if (pos >= 0)
      {
         AuctionServerThread toTerminate = clients[pos];
         System.out.println(" Removing client thread " + ID + " at " + pos);

         if (pos < clientCount-1)
            for (int i = pos+1; i < clientCount; i++)
               clients[i-1] = clients[i];
         clientCount--;
         try{
         toTerminate.close();
         }
         catch(IOException ioe)
         {
         System.out.println(" Error closing thread: " + ioe);
         }
         toTerminate = null;
         System.out.println(" Client " + pos + " removed");

         //timer.cancel();
         //timer.purge();

         notifyAll();
      }
   }

   private void addThread(Socket socket)
   {
    if (clientCount < clients.length){

     System.out.println(" Client accepted: " + socket);
         clients[clientCount] = new AuctionServerThread(this, socket);
         try{
            clients[clientCount].open();
            clients[clientCount].start();
            clientCount++;

            for (int i = 0; i < clientCount; i++)
            {
               clients[i].send("| Current " + currentItem);
               clients[i].send("Please enter a bid:");
            }


            for (int i = 0; i < clientCount; i++)
            {
               //String itemName1 = item1.itemName;
               //int currentBid1 = item1.currentBid;
               //System.out.println(item1);
               //clients[i].send(item1 + "has curently at a bid of: $"); // sends messages to clients
            }

            // if (clientCount <= 1)
            // {
            //    timer.cancel();
            //    timer.purge();
            // }
         }
         catch(IOException ioe){
       System.out.println(" Error opening thread: " + ioe);
      }
    }
      else
         System.out.println(" Client refused: maximum " + clients.length + " reached.");
   }

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
         // ArrayList<Integer> items = new ArrayList<>();
         // items.add(100);
         // items.add(280);
         // items.add(500);
         // items.add(250);
         // items.add(350);
         // for (int i = 0; i < items.size(); i++) 
         // {
         //    int value = items.get(i);
         //    int j = i+1;
         //    System.out.println("Item " + j + ": "+ value);
         // }
         //itemFiller();

         // for (int i = 0; i < items.size(); i++) 
         // {
         //    int value = items.get(i);
         //    int j = i+1;
         //    System.out.println("Item " + j + ": "+ value);
         // }
   }
}
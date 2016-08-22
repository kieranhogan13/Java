
// Week 5 - Task 7
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class TCPSerServer
{
   private static ServerSocket servSock;
   private static final int PORT = 1234;

   public static void main(String[] args)
   {
      System.out.println("!!!Opening port...\n");
      try
      {
         servSock = new ServerSocket(PORT);
      }
      catch(IOException e)
      {
         System.out.println("Unable to attach to port!");
         System.exit(1);
      }
      do
      {
         run();
      }while (true);
   }

   private static void run()
   {
		Socket link = null;
		try{
			link = servSock.accept();

			PrintWriter out = new PrintWriter(link.getOutputStream(),true);
			ObjectInputStream istream = new ObjectInputStream (link.getInputStream());
			Person p = null;

			while(true){
				try{

					p = (Person)istream.readObject();
					System.out.println("SERVER - Received: New object.\n");
					System.out.println("SERVER - Received: Person name=" + p.getName());
					System.out.println("SERVER - Received: Person age=" + p.getAge());
					System.out.println("SERVER - Received: Person address=" + p.getAddress());
					out.println("Person object received.");

				}
				catch (Exception e) {
					System.out.println("Exception in run");
					System.out.println("\n* Closing connection... *");
					break;

				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		finally
		{
			try
			{
				System.out.println("\n* Closing connection... *");
				link.close();
			}
			catch(IOException e)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}

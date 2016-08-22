//Client.

import java.rmi.*;

public class HelloClient
{
   private static final String HOST = "localhost";

   public static void main(String[] args)
   {
		//Here's the new code...
		if (System.getSecurityManager() == null)
		{
			System.setSecurityManager(
									new ZeroSecurityManager());
		}

  		try
		{
			Hello greeting =
				(Hello)Naming.lookup("rmi://" + HOST + "/Hello");

			System.out.println("Message received: "
								   + greeting.getGreeting());
		}
			catch(ConnectException conEx)
		{
			System.out.println("Unable to connect to server!");
			System.exit(1);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}
}

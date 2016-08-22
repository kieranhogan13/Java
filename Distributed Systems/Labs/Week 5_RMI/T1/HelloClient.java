//Client.
import java.rmi.*;

public class HelloClient
{
	private static final String HOST = "localhost";

	public static void main(String[] args)
	{
		try
		{
			Hello greeting =
				(Hello)Naming.lookup("rmi://" + HOST + "/Hello");

			//Simply retrieve and display greeting...
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


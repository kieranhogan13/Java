//Client.
import java.rmi.*;

public class HelloClient
{
	public static void main(String[] arg)
	{
		String HOST = arg[0];
		try
		{
			if (arg.length < 1)
			{
				System.out.println("You must supply a hostname: ");
				return;
			}
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


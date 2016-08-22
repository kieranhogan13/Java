// Week 5 - Task 6

import java.io.*;
import java.net.*;

public class ConsumerClient
{
	private static InetAddress host;
	private static final int PORT = 1234;
	private static Socket link;
	private static BufferedReader in;
	private static PrintWriter out;
	private static BufferedReader keyboard;

	public static void main(String[] args) throws IOException
	{
		try
		{
			host = InetAddress.getLocalHost();
			link = new Socket(host, PORT);
			in = new BufferedReader(
					new InputStreamReader(link.getInputStream()));
			out = new PrintWriter(link.getOutputStream(),true);

			keyboard = new BufferedReader(
						new InputStreamReader(System.in));

			int request = 0;
			do
			{
				do
				{
					System.out.print("\nEnter 1 for resource or 0 to quit: ");
					request = Integer.parseInt(keyboard.readLine());
					if (request<0||request>1)
						System.out.println("\n*** Invalid! ***\n");
				}while (request<0||request>1);
				out.println(request);
				if (request!=0)
				{
					String response = in.readLine();
					System.out.println("\nSERVER>" + response);
				}
			}while (request==1);
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("\nHost ID not found!\n");
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				link.close();
			}
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
			}
		}
	}
}



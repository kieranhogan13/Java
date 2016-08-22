//
// Week 2 - Task 7
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPGuessClient
{
	private static InetAddress host;
	private static final int PORT = 1234;
	private static DatagramSocket dgramSocket;
	private static DatagramPacket inPacket, outPacket;
	private static byte[] buffer;



	public static void main(String[] args)
	{
		try
		{
			host = InetAddress.getLocalHost();

		}
		catch(UnknownHostException e)
		{
			System.out.println("Host ID not found!");
			System.exit(1);
		}
		run();
	}

	private static void run()
	{
		try
		{
			dgramSocket = new DatagramSocket();

			//Set up stream for keyboard entry...
			BufferedReader userEntry =
					new BufferedReader
				 		(new InputStreamReader(System.in));

			String message="", response="";
			do{

				System.out.print("Enter number: ");
				message = userEntry.readLine();
				// TODO:Check if it is an integer
				outPacket = new DatagramPacket(
								message.getBytes(),
								message.length(),
								host,PORT);
				dgramSocket.send(outPacket);
				buffer = new byte[256];
				inPacket = new DatagramPacket(
					buffer, buffer.length);
				dgramSocket.receive(inPacket);

				response = new String(inPacket.getData(),
							0, inPacket.getLength());
				System.out.println(
						"\nSERVER>" + response);

			}while (!response.equals("CORRECT"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		finally
		{
			System.out.println("\n* Closing connection... *");
			dgramSocket.close();
		}
	}
}

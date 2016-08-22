// Week 2 - Task 7

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.lang.Integer;


public class UDPGuessServer
{
	private static final int PORT = 1234;
	private static DatagramSocket dgramSocket;
	private static DatagramPacket inPacket, outPacket;
	private static byte[] buffer;
	private static int randomNumber;

	public static void main(String[] args)
	{
		System.out.println("Opening port...\n");
		try
		{
			dgramSocket = new DatagramSocket(PORT);
			randomNumber = (int)(Math.random() * 100);
			System.out.println("randomNumber=" + randomNumber);

		}
		catch(SocketException e)
		{
			System.out.println("Unable to attach to port!");
			System.exit(1);
		}
		run();
	}

	private static void run()
	{
		try
		{
			String messageIn,messageOut;
			Integer ranNum = new Integer(randomNumber);

			do
			{
				buffer = new byte[256];
				inPacket = new DatagramPacket(
						buffer, buffer.length);
				dgramSocket.receive(inPacket);

				InetAddress clientAddress =
						inPacket.getAddress();
				int clientPort =
						inPacket.getPort();

				messageIn = new String(inPacket.getData(),0,
						inPacket.getLength());
				System.out.println("Server> message received:" + messageIn);

				// check if it is number
				try{
					Integer rNum = Integer.decode(messageIn);
					if(rNum.compareTo(ranNum) > 0){
						messageOut = "LOWER";
					}
					else if(rNum.compareTo(ranNum) < 0){
						messageOut = "HIGHER";
					}
					else{
						messageOut="CORRECT";
					}
				}
				catch(NumberFormatException nFe){
						messageOut="Not a number";
				}
				outPacket = new DatagramPacket(
							messageOut.getBytes(),
							messageOut.length(),
							clientAddress,
							clientPort);
				dgramSocket.send(outPacket);
			}while (true);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		finally		//If exception thrown, close connection.
		{
			System.out.println("\n* Closing connection... *");
			dgramSocket.close();
		}
	}
}

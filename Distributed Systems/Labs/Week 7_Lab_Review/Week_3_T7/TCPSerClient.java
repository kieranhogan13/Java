// Wek 5 - T7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.net.Socket;
import java.net.InetAddress;
import java.io.*;


public class TCPSerClient
{
	private static InetAddress host;
	private static final int PORT = 1234;

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
		Socket link = null;

		try
		{
			link = new Socket(host,PORT);

			BufferedReader in =
				new BufferedReader
					(new InputStreamReader
					   	(link.getInputStream()));

			ObjectOutputStream out = new ObjectOutputStream(link.getOutputStream());

			BufferedReader userEntry =
					new BufferedReader
				 		(new InputStreamReader(System.in));

            String name, address, strAge;
            int age;

            while(true){
				ObjectOutputStream ostream =
							new ObjectOutputStream(new FileOutputStream("person.dat"));
				System.out.print("New object\n");
				System.out.print("Enter name: ");
				name = userEntry.readLine();
				System.out.print("Enter age: ");
				strAge = userEntry.readLine();
				Integer iAge;
				try {
					iAge = Integer.decode(strAge);
					age = iAge.intValue();

					System.out.print("Enter address: ");
					address = userEntry.readLine();

					Person personObj = new Person(name, age, address);
					ostream.writeObject(personObj);
					ostream.close();
					ObjectInputStream istream =
								new ObjectInputStream(
									new FileInputStream("person.dat"));

					try {

						Person p = (Person)istream.readObject();//Typecast.
						System.out.println("Client:name= " + p.getName());
						System.out.println("Client:age= " + p.getAge());
						System.out.println("Client:address=" + p.getAddress());

						String response, message;
						out.writeObject(p);
						response = in.readLine();
						System.out.println("\nSERVER> " + response);

					}
					catch(ClassNotFoundException cnfe){
						System.out.println("cnfe=" + cnfe.toString());
					}

				}
				catch(NumberFormatException nFe){
					System.out.print("Age:Not a number.\n ");

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
				System.out.println(
							"\n* Closing connection... *");
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

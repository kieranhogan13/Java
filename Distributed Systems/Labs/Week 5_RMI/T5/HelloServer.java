//Server.
import java.rmi.*;

public class HelloServer
{
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception
	{
		HelloImpl temp = new HelloImpl();

		String rmiObjectName = "rmi://" + HOST + "/Hello";
		//Could omit host name, since 'localhost' would be
		//assumed by default.

		Naming.rebind(rmiObjectName,temp);
		System.out.println("Binding complete...\n");
	}
}



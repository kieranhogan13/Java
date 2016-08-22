//Server.
import java.rmi.*;

public class Server {

	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception {
		MessageServerImpl temp = new MessageServerImpl();
		String rmiObjectName = "rmi://" + HOST + "/message";
		Naming.rebind(rmiObjectName,temp);
		System.out.println("Binding complete...\n");
	}
}

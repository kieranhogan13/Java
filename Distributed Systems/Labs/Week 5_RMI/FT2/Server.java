//Server.
import java.rmi.*;

public class Server {
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception {
		// set the security manager - now our applications will run wit this
		// invoked and preventing access
		System.setSecurityManager(new RMISecurityManager());

		MessageServerImpl temp = new MessageServerImpl();
		String rmiObjectName = "rmi://" + HOST + "/message";
		Naming.rebind(rmiObjectName,temp);
		System.out.println("Binding complete...\n");
	}
}


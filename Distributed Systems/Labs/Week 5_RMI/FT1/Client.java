//Client.
import java.rmi.*;
import java.io.*;

public class Client {

	private final String HOST = "localhost";
	private Listener listener;
	private LogManager logManager;

	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		
		try {
			// this is a remote object - a stub will be sent
			listener = new ListenerImpl();

			// this is not a remote object - a copy will be sent
			logManager = new LogManager();

			MessageServer messageServer = (MessageServer)Naming.lookup("rmi://" + HOST + "/message");
			messageServer.register(listener, logManager);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("DO YOU WANT A MESSAGE (YES | NO) :/> ");
			String line = br.readLine();
			while(line.equalsIgnoreCase("Y") || line.equalsIgnoreCase("YES")) {
				messageServer.tellMeSomething();
				System.out.print("DO YOU WANT A MESSAGE (YES | NO) :/> ");
				line = br.readLine();
			}
			br.close();
			System.exit(0);
        }
		catch(ConnectException conEx) {
			System.out.println("Unable to connect to server!");
			System.exit(1);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}


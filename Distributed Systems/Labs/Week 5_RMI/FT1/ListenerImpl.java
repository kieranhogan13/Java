//Implementation of RMI interface.
import java.rmi.*;
import java.rmi.server.*;

public class ListenerImpl extends UnicastRemoteObject implements Listener {

	public ListenerImpl() throws RemoteException {}
	
	public void theMessageIs(String message) throws RemoteException {
		System.out.println(message);
	}

}


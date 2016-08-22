//RMI interface.
import java.rmi.*;

public interface Listener extends Remote {
	public void theMessageIs(String message) throws RemoteException;
}


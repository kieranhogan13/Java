//RMI interface.
import java.rmi.*;

public interface MessageServer extends Remote {
	public void register(Listener listener, LogManager logManager) throws RemoteException;
	public void tellMeSomething() throws RemoteException;
}


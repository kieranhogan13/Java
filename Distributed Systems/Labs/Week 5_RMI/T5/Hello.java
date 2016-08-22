//RMI interface.
import java.rmi.*;

public interface Hello extends Remote
{
	public String getGreeting() throws RemoteException;
}


//Implementation of RMI interface.
import java.rmi.*;
import java.rmi.server.*;

public class HelloImpl extends UnicastRemoteObject implements Hello
{
	public HelloImpl() throws RemoteException
	{
		//No action needed here (but must specify constructor).
	}

	public String getGreeting() throws RemoteException
	{
		return ("Hello there!");
	}
}


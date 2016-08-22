//Implementation of RMI interface.
import java.rmi.*;
import java.rmi.server.*;

public class MessageServerImpl extends UnicastRemoteObject implements MessageServer {
	private Listener listener;
	private LogManager logManager;

	private String quotes[] = 
		{ "A fanatic is one who can't change his mind and won't change the subject",
		"Although prepared for martyrdom, I preferred that it be postponed",
		"Broadly speaking, the short words are the best, and the old words best of all",
		"He has all the virtues I dislike and none of the vices I admire",
		"History will be kind to me for I intend to write it",
		"It has been said that democracy is the worst form of government except all the others that have been tried",
		"It is a mistake to try to look too far ahead. The chain of destiny can only be grasped one link at a time",
		"Never hold discussions with the monkey when the organ grinder is in the room",
		"Personally I'm always ready to learn, although I do not always like being taught",
		"The price of greatness is responsibility",
		"When the eagles are silent, the parrots begin to jabber"};

	public MessageServerImpl() throws RemoteException {
		//No action needed here (but must specify constructor).
	}

	public void register(Listener listener, LogManager logManager) throws RemoteException {
		this.listener = listener;
		this.logManager = logManager;
	}

	public void tellMeSomething() throws RemoteException {
		String thisMessage = quotes[(int)((double)quotes.length * Math.random())];
		try {
			// this is a remote method being called on the callback object
			// we only have a stub for this on the server side
			listener.theMessageIs(thisMessage);
		} catch (RemoteException r) {}
		
		// the entire logManager object was serialised, copied and sent over to us
		// on the server side - when we call its methods the methods on our copy
		// get executed - not the methods on the object on the client side
		logManager.writeToLog("Message sent...");
	}
}


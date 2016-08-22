//Implementation of RMI interface.
import java.rmi.*;
import java.rmi.server.*;

public class BankImpl extends UnicastRemoteObject implements Bank {
	private int acctNum;
	private String surname;
	private String firstNames;
	private double balance;

	public BankImpl (int acctNo, String sname, String fnames, double bal) throws RemoteException {
		acctNum = acctNo;
		surname = sname;
		firstNames = fnames;
		balance = bal;
	}

	public int getAcctNum() throws RemoteException {
		return acctNum;
	}

	public String getName() throws RemoteException {
		return (firstNames + " " + surname);
	}

	public double getBalance() throws RemoteException {
		return balance;
	}

	public double withdraw(double amount) throws RemoteException {
		if (amount <= balance) return amount;
		else return 0;
	}

	public void deposit(double amount) throws RemoteException {
		if (amount > 0) balance += amount;
	}
}

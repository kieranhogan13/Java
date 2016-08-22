//Server.
import java.rmi.*;

public class BankServer {

	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception {
		BankImpl[] account =
			{new BankImpl(111111, "Smith", "Fred James", 112.58),
			new BankImpl(222222, "Jones", "Sally", 507.85),
			new BankImpl(234567, "White", "Mary Jane", 2345.00),
			new BankImpl(666666, "Satan", "Beelzebub", 666.00)};

		for (int i=0; i<account.length; i++) {
			int acctNum = account[i].getAcctNum();
			Naming.rebind("//" + HOST + "/account" + acctNum, account[i]);
		}
		System.out.println("Binding complete...\n");
	}
}



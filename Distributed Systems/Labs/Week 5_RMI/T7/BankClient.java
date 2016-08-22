//Client.
import java.rmi.*;

public class BankClient {

	private static final String HOST = "localhost";
	private static final int[] acctNum = {111111, 222222, 234567, 666666};

	public static void main(String[] args) {
		try {
			for (int i=0; i<acctNum.length; i++) {
				Bank temp = (Bank)Naming.lookup("rmi://" + HOST + "/account" + acctNum[i]);
				System.out.println("\nAccount number: " + temp.getAcctNum()); 
				System.out.println("Name: " + temp.getName());
				System.out.println("Balance: " + temp.getBalance());
			}
		} catch(ConnectException conEx) {
			System.out.println("Unable to connect to server!");
			System.exit(1);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}


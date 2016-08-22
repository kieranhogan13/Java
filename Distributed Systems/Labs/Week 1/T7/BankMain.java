public class BankMain {

	public static void main(String args[]) {

		// create 2 bank accounts
		BankAccount b1 = new CreditCardAccount(200, "Ciaran", 1000);
		BankAccount b2 = new SavingsAccount(300, "John");

		// try to withdraw money from the CreditCardAccount
		// if there is not enough money an exception will be thrown
		// The exception will contain a message indicating what the problem was.
		// You will meet several different types of exceptions throughout the year.
		// All exceptions are subclasses of the class "Exception"
		try {
			b1.withdraw(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			b2.withdraw(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}


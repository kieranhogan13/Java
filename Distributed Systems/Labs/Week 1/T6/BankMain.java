public class BankMain {

	public static void main(String args[]) {

		// create a single object, and store with a 
		// reference to BankAccount
		BankAccount b1 = new CreditCardAccount(0, "Ciaran", 1000);
		
		// call a method
		int x = b1.getBalance();
		
		b1.deposit(Integer.parseInt(args[0]));	

		// print out
		System.out.println("The balance in b1 is " + x);

		b1.withdraw(Integer.parseInt(args[1]));	

		// create an array of bank accounts
		// this array can store any type of bank account 
		// (SavingsAccount or CreditCardAccount)
		BankAccount array[] = new BankAccount[3];
		array[0] = b1;
		array[1] = new SavingsAccount();
		array[2] = new SavingsAccount(2000, "John");

		// loop through the array of bank accounts
		// printing out the balance
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i].getBalance());
		}

		// loop through the array of bank accounts
		// counting the number of each type of object
		int numberOfSavingsAccounts = 0;
		int numberOfCreditCardAccounts = 0;
		for(int i = 0; i < array.length; i++) {

			// if the object is SavingsAccount, increment count
			if(array[i] instanceof SavingsAccount) {
				numberOfSavingsAccounts++;
			}
			else if(array[i] instanceof CreditCardAccount) {
				numberOfCreditCardAccounts++;
				// if you want to treat the object as a CreditCardAccount
				// perform a "cast"
				// this creates a new reference that can be used for all the
				// methods in CreditCardAccount
				CreditCardAccount c = (CreditCardAccount)array[i];
				System.out.println("Credit card account number " + numberOfCreditCardAccounts + 
					" has a credit limit of " + c.getCreditLimit());
			}
		}

		System.out.println("Number of Savings Accounts is " + numberOfSavingsAccounts);
		System.out.println("Number of Credit Card Accounts is " + numberOfCreditCardAccounts);
	}
}


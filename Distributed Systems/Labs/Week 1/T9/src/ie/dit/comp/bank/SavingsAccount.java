package ie.dit.comp.bank;

/**
	Savings account, where amounts can be deposited and withdrawn. Deposited amounts must be at least E100.
	Extends BankAccount and implements Tellable (since this is implemented at a higher level)
	@author Ciarán O'Leary
*/
public class SavingsAccount extends BankAccount implements InterestGainer {

	/** 
		Default constructor, sets the balance to 0, and the name to an empty string
	*/
	public SavingsAccount() {
		this(0, ""); // call the other constructor
	}
	
	/**
		Constructor to read in balance and name
		@param balance The initial balance of the account
		@param name The name of the account 
	*/
	public SavingsAccount(int balance, String name) {
		super(balance, name); // call the parent class constructor
	}
	
	/**
		Takes an int parameter specifying how much is to be deposited, this 
		will be added to the balance, only if it exceeds E100. Over-rides the abstract method from above.
		@param amt Amount to be deposited
	*/
	public void deposit(int amt) {
		balance += amt;
	}

	/**
		Takes an int parameter specifying how much is to be withdrawn, 
		this will be subtracted from the balance, only if there are sufficient 
		funds. Over-rides the abstract method from above.
		@param amt Amount to be withdrawn
	*/
	public void withdraw(int amt) throws Exception {
		if(amt < balance) {
			balance -= amt;
		}
		else {
			throw new Exception("Insufficient funds");
		}
	}

	/**
		This metohd is inherited from the interface.
		This must be implemented.
	*/
	public void addInterest() {
		balance *= 2; // double the balance.
	}
}

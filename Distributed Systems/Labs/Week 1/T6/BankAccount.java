/**
	Abstract base class for a Bank Account. Implements the Tellable interface which prints out details about the object.
	@author Ciarán O'Leary
*/
public abstract class BankAccount {

	/** The balance in the account */
	protected int balance;

	/** The name of the account */
	protected String name;

	/** 
		Default constructor, sets the balance to 0, and the name to an empty string
	*/
	public BankAccount() {
		this(0, ""); // call the other constructor
	}
	
	/**
		Constructor to read in balance and name
		@param balance The initial balance of the account
		@param name The name of the account 
	*/
	public BankAccount(int balance, String name) {
		this.balance = balance; // 'this' keyword represents the varaible attached to the object, 
								// used to distinguish from the parameter passed in which has local scope
		this.name = name;
	}
	
	/**
		Returns the balance in the account
		@return Balance in the account
	*/
	public int getBalance() {
		return balance;
	}

	/**
		Abstract method, takes an int parameter, will be overridden by sub classes
		@param amt Amount to be deposited
		@return true if the deposit operation succeeded, false otherwise
	*/
	public abstract void deposit(int amt);

	/**
		Abstract method, takes an int parameter, will be overridden by sub classes
		@param amt Amount to be withdrawn
		@return true if the withdraw operation succeeded, false otherwise
	*/
	public abstract void withdraw(int amt);

}

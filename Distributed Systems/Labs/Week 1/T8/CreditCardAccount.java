/**
	Credit Card Account where a credit limit can be specified, and accounts can be overdrawn up to this amount.
	Extends BankAccount and implements Tellable (since this is implemented at a higher level)
	@author Ciarán O'Leary
*/
public class CreditCardAccount extends BankAccount {

	private int creditLimit;

	/** 
		Default constructor, sets the balance to 0, and the name to an empty string
	*/
	public CreditCardAccount() {
		this(0, "", 0); // call the third constructor
	}
	
	/**
		Constructor to read in balance and name
		@param balance The initial balance of the account
		@param name The name of the account 
	*/
	public CreditCardAccount(int balance, String name) {
		this(balance, name, 0); // call the third constructor
	}
	
	/**
		Constructor to read in balance, name and credit limit
		@param balance The initial balance of the account
		@param name The name of the account 
		@param creditLimit The amount by which the account can be over drawn
	*/
	public CreditCardAccount(int balance, String name, int creditLimit) {
		super(balance, name);
		this.creditLimit = creditLimit;
	}
	
	/**
		Returns the credit limit in the account
		@return Amount by which the account can be over drawn
	*/
	public int getCreditLimit() {
		return creditLimit;
	}

	/**
		Takes an int parameter specifying how much is to be deposited, this 
		will be added to the balance. Over-rides the abstract method from above.
		@param amt Amount to be deposited
	*/
	public void deposit(int amt) {
		balance += amt;
	}

	/**
		Takes an int parameter specifying how much is to be withdrawn, 
		this will be subtracted from the balance, only if there are sufficient 
		funds (including the credit limit). Over-rides the abstract method from above.
		@param amt Amount to be withdrawn
		@throws CreditCardAccountOverdrawnException Thrown if there are insufficient funds available for this withdrawl (including credit limit)
	*/
	public void withdraw(int amt) throws Exception {
		if(amt < (balance + creditLimit)) {
			balance -= amt;
		}
		else {
			throw new Exception ("Insufficient funds (including credit limit)");
		}
	}
}

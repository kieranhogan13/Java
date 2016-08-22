/**
	Interface to allow any class that wants to implement addInterest do so.
	Interfaces are fully abstract and only show what methods are possible.
	They allow for the type of multiple inheritance C++ allows - but only in 
	a limited sense.

	This interface will be implemented by SavingsAccount, but not by CreditCardAccount
	@author Ciarán O'Leary
*/
public interface InterestGainer {

	public void addInterest();

}

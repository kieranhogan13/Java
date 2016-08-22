import ie.dit.comp.bank.*;

public class BankMain {

	public static void main(String args[]) {

		BankAccount b = new SavingsAccount(300, "John");

		System.out.println("Before interest added " + b.getBalance());

		// this will not work since addInterest is not a method on BankAccount
		// b2.addInterest();

		// must cast to either SavingsAccount, or InterestGainer
		InterestGainer i = (InterestGainer)b;
		i.addInterest();

		System.out.println("After interest added " + b.getBalance());

	}
}


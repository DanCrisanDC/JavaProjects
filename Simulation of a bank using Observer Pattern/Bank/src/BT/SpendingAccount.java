package BT;

public class SpendingAccount extends Account{

	private static final long serialVersionUID = 1113799434508676095L;

	public SpendingAccount(String accountIban, double amount) {
		super(amount, "SpendingAccount");
	}

}

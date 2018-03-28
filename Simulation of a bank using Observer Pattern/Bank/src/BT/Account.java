package BT;

import java.io.Serializable;
import java.util.Observable;

public class Account extends Observable implements Serializable {

	private static final long serialVersionUID = 1113799434508676095L;
	private String accountIban;
	private double amount;
	private String type;
	
	public Account(double amount, String type) {
		this.accountIban = this.generateIban();
		this.amount = amount;
		this.type = type;
	}

	public Account() {
	}

	public String getAccountIban() {
		return accountIban;
	}

	public void setAccountIban(String accountIban) {
		this.accountIban = accountIban;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void addAmount(double amount) {
		this.setAmount(amount+this.amount);
		this.setChanged();
		this.notifyObservers("Deposit has been made");
	}
	
	public void removeAmount(double amount) {
		if (amount<= this.getAmount()) {
			this.setAmount(this.amount-amount);
			this.setChanged();
			this.notifyObservers(amount + " have been withdrawn");
		} else {
			System.out.println("Not enough money!\n");
		}
		if (this.getAmount()==0)
			System.out.println("The account " + this.accountIban + " is empty!\n");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
	
	private String generateIban() {
		String begin="RO";
		String iban = "";
		for (int i=0; i<10; i++) {
			iban += Integer.toString((int) (Math.random()*10));
		}
		return begin+iban;
	}
}

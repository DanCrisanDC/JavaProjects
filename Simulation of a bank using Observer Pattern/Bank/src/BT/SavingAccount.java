package BT;

public class SavingAccount extends Account{

	private static final long serialVersionUID =1113799434508676095L;
	private double rate;
	private int periodOfTime;
	private double amountAdded;
	private double amountWithdrawn;
	private boolean isAdded;
	private boolean isWithdrawn;
	private double simpleInterest;
	
	public SavingAccount(String accountIban, double amount, int time) {
		super(amount, "SavingAccount");
		this.rate = 0.05;
		this.periodOfTime=time;
		this.isAdded=true;
		this.isWithdrawn=false;
		this.simpleInterest=0;
		super.setAmount(amount+this.getInterestRate());
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getPeriodOfTime() {
		return periodOfTime;
	}

	public void setPeriodOfTime(int periodOfTime) {
		this.periodOfTime = periodOfTime;
	}

	public double getAmountAdded() {
		return amountAdded;
	}

	public double getAmountWithdrawn() {
		return amountWithdrawn;
	}

	public void addMoney(double amount) {
		if (!this.isAdded) {
			super.addAmount(amount);
			this.setAdded(true);
			this.notifyObservers("Deposit has been made");
		}
		else 
			System.out.println("Money already added!\n");
	}

	public void withdrawMoney() {
		if (!this.isWithdrawn) {
			this.setChanged();
			this.notifyObservers(super.getAmount() + this.getInterestRate() + "Money has been withdrawn");
			super.setAmount(0);
			this.setWithdrawn(true);	
		}
		else 
			System.out.println("Money already withdrawn!\n");
	}
	
	public boolean isAdded() {
		return isAdded;
	}

	public void setAdded(boolean isAdded) {
		this.isAdded = isAdded;
	}

	public boolean isWithdrawn() {
		return isWithdrawn;
	}

	public void setWithdrawn(boolean isWithdrawn) {
		this.isWithdrawn = isWithdrawn;
	}

	public double getSimpleInterest() {
		return simpleInterest;
	}

	public void setSimpleInterest(double simpleInterest) {
		this.simpleInterest = simpleInterest;
	}

	public double getInterestRate() {
		double interestRate = this.getRate()*this.periodOfTime*super.getAmount();
		this.setSimpleInterest(interestRate);
		return interestRate;
	}
	
	
}

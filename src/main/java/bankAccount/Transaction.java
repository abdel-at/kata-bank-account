package bankAccount;

public class Transaction {

	private Account from; 
	private Account to; 
	private double amount;
	
	public Transaction(Account from, Account to, double amount) {
		super();
		this.from = from;
		this.to = to;
		this.amount = amount;
	}
	public Account getFrom() {
		return from;
	}
	public void setFrom(Account from) {
		this.from = from;
	}
	public Account getTo() {
		return to;
	}
	public void setTo(Account to) {
		this.to = to;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
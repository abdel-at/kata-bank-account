package bankAccount;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private double balance;
	
	private List<Transaction> transactionHistory = new ArrayList<Transaction>();
	
	public List<Transaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Account() {
	}
	
	public Account(double balance) {
		this.balance = balance;
		
	}
	
	/**
	 * depose the amount to the account
	 * @param amount
	 */
	public void deposeAmount(double amount) {
		checkAmount(amount);
		this.balance += amount;
	}
	
	/**
	 * withdraw the amount from the account
	 * @param amount
	 */
	public void withdrawAmount(double amount) {
		checkAmount(amount);
		this.balance -= amount;
	}
	
	/**
	 * check if the amount is valid
	 * @param amount
	 */
	public void checkAmount(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("invalid amount");
		}
	}

	/**
	 * transfer an amount from the current account to the param's account
	 * @param payee the account who recieves the amount
	 * @param amount the transfered amount
	 */
	public void transferTo(Account payee, double amount) {
		
		this.withdrawAmount(amount);
		payee.deposeAmount(amount);
		
		this.recordTransfers(payee, amount);
		
	}
	
	/**
	 * Record the transaction after transfers
	 * @param payee
	 * @param amount
	 */
	public void recordTransfers(Account payee, double amount) {
		checkAmount(amount);
		Transaction transaction = new Transaction(this, payee, amount);
		this.getTransactionHistory().add(transaction);
		payee.getTransactionHistory().add(transaction);

	}

	public long getCountWherePayer() {
		return this.transactionHistory.stream().filter(t -> t.getFrom().equals(this)).count();
	}

	public long getCountWherePayee() {
		return this.transactionHistory.stream().filter(t -> t.getTo().equals(this)).count();
	}
}


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
	 * method to withdraw or deposit in/from an account 
	 * @param amount the amount to depose/withdraw
	 * @param withdraw true for withdraw, false for deposit 
	 */
	
	public void withdrawOrDeposit(double amount, boolean withdraw) {
		if(withdraw) {
			this.balance -= amount;
		}else {
			this.balance += amount;
		}
	}

	/**
	 * transfer an amount from the current account to the param's account
	 * @param payee the account who recieves the amount
	 * @param amount the transfered amount
	 */
	public void transferTo(Account payee, double amount) {
		Transaction transaction = new Transaction(this, payee, amount);
		
		this.withdrawOrDeposit(amount, true);
		this.getTransactionHistory().add(transaction);

		payee.withdrawOrDeposit(amount, false);
		payee.getTransactionHistory().add(transaction);

		
	}

	public long getCountWherePayer() {
		return this.transactionHistory.stream().filter(t -> t.getFrom().equals(this)).count();
	}

	public long getCountWherePayee() {
		return this.transactionHistory.stream().filter(t -> t.getTo().equals(this)).count();
	}
}


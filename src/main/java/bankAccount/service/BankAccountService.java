package bankAccount.service;

import org.springframework.stereotype.Component;

import bankAccount.dto.Account;
import bankAccount.dto.Transaction;

@Component
public class BankAccountService {
	
	/**
	 * depose the amount to the account
	 * @param amount
	 */
	synchronized public void  deposeAmount(Account account, double amount) {
		checkAmount(amount);
		account.setBalance(account.getBalance() + amount);
	}
	
	/**
	 * withdraw the amount from the account
	 * @param amount
	 */
	synchronized public void withdrawAmount(Account account, double amount) {
		checkAmount(amount);
		account.setBalance(account.getBalance() - amount);
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
	public void transferTo(Account payer, Account payee, double amount) {
		
		withdrawAmount(payer, amount);
		deposeAmount(payee, amount);
		
		recordTransfers(payer, payee, amount);	
	}
	
	/**
	 * Record the transaction after transfers
	 * @param payee
	 * @param amount
	 */
	public void recordTransfers(Account payer, Account payee, double amount) {
		checkAmount(amount);
		Transaction transaction = new Transaction(payer, payee, amount);
		payer.getTransactionHistory().add(transaction);
		payee.getTransactionHistory().add(transaction);
	}

	public long getCountWherePayer(Account payer) {
		return payer.getTransactionHistory().stream().filter(t -> t.getFrom().equals(payer)).count();
	}

	public long getCountWherePayee(Account payee) {
		return payee.getTransactionHistory().stream().filter(t -> t.getTo().equals(payee)).count();
	}
}

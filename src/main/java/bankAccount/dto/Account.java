package bankAccount.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Account {

	private double accountId;
	private double balance;
	private List<Transaction> transactionHistory = new ArrayList<>();
	
	public Account() {
	}


	public Account(double balance, double id) {
		this.balance = balance;
		this.accountId = id;	
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true; 
		Account account = (Account) obj;
		return this.accountId == account.getAccountId();
	}
	
}

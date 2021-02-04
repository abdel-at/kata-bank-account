package bankAccount.dto;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	private Account from; 
	private Account to; 
	private double amount;
	

}
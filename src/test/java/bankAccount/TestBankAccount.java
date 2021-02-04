package bankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bankAccount.service.BankAccountService;
import bankAccount.dto.Account;

public class TestBankAccount {
	
	private BankAccountService bankAccountService;
	
	private Account testAccount;
	private Account payer; 
	private Account payee; 
	private Account testedAccount; 
	private Account accountForTransaction;
	private Account secondAccountForTransaction;
	
	@Before
	public void init() {
		testAccount = new Account(1000, 1);
		payer = new Account(1500, 2);
		payee = new Account(500, 3);
		testedAccount = new Account(100, 4);
		accountForTransaction = new Account(100, 5);
		secondAccountForTransaction = new Account(100, 6);
		
		bankAccountService = new BankAccountService();
	}
	
	
	@Test
	public void testWithdraw() {
		this.bankAccountService.withdrawAmount(testAccount, 500);
		assertEquals(testAccount.getBalance(), 500, 10);
	}
	
	@Test
	public void testDeposit() {
		this.bankAccountService.deposeAmount(testAccount, 300);
		assertEquals(testAccount.getBalance(), 1300, 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkAmount_fail_when_negativeEntry() {
		bankAccountService.checkAmount(-100);
	}
	
	@Test
	public void testTransfer() {
		double payerBalance = payer.getBalance();
		double payeeBalance = payee.getBalance();
		bankAccountService.transferTo(payer, payee, 500);
		assertEquals(payer.getBalance() + 500 ,  payerBalance, 10);
		assertEquals(payee.getBalance() - 500  , payeeBalance, 10);
		
	}
	
	@Test
	public void testRecordingTransfers() {
		
		bankAccountService.recordTransfers(payer, payee, 500d);
		assertEquals(payer.getTransactionHistory().get(0).getTo(), payee);
		assertEquals(payee.getTransactionHistory().get(0).getFrom(), payer);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void recordingTransfer_Failure() {
		bankAccountService.recordTransfers(payer, payee, -500d);
	}
	
	@Test
	public void functionnalTransferTest() {
		double amount = 100;
		bankAccountService.transferTo(payer, payee, amount);
		
		assertEquals(payer.getBalance(), 1400, 10);
		assertEquals(payee.getBalance(), 600, 10);
		
		assertEquals(payer.getTransactionHistory().get(0).getTo(), payee);
		assertEquals(payer.getTransactionHistory().get(0).getAmount() , 100, 10);
	}
	
	@Test
	public void testQueryTransactionHistory() {
		bankAccountService.transferTo(testedAccount, accountForTransaction, 100);
		
		bankAccountService.transferTo(accountForTransaction, testedAccount, 100);
		bankAccountService.transferTo(secondAccountForTransaction, testedAccount, 100);
		
		
		long fromTestedAccount = bankAccountService.getCountWherePayer(testedAccount);
		long toTestedAccount = bankAccountService.getCountWherePayee(testedAccount);
		
		assertEquals(fromTestedAccount , 1, 0);
		assertEquals(toTestedAccount , 2,0 );
	}

	
}

package bankAccount;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestBankAccount {
	
	private Account testAccount,payer, payee, testedAccount, tr1,tr2;
	
	@Before
	public void init() {
		testAccount = new Account(1000);
		payer = new Account(1500);
		payee = new Account(500);
		testedAccount = new Account(100);
		tr1 = new Account(100);
		tr2 = new Account(100);
	}
	
	
	@Test
	public void testWithdrawAndDeposit() {
		testAccount.withdrawOrDeposit(500, true);
		assertTrue(testAccount.getBalance() == 500);
		testAccount.withdrawOrDeposit(500, false);
		assertTrue(testAccount.getBalance() == 1000);
		
	}
	
	@Test
	public void testTransferAndTransactionHistory() {
		double payerBalance = payer.getBalance();
		double payeeBalance = payee.getBalance();
		payer.transferTo(payee, 500);
		assertTrue(payer.getBalance() + 500 == payerBalance);
		assertTrue(payee.getBalance() - 500 == payeeBalance);
		
		payer.getTransactionHistory().get(0).getTo().equals(payee);
		payee.getTransactionHistory().get(0).getFrom().equals(payer);
	}
	
	@Test
	public void testQueryTransactionHistory() {
		testedAccount.transferTo(tr1, 100);
		tr1.transferTo(testedAccount, 100);
		tr2.transferTo(testedAccount, 100);
		
		long fromTestedAccount = testedAccount.getCountWherePayer();
		long toTestedAccount = testedAccount.getCountWherePayee();
		
		assertTrue(fromTestedAccount == 1);
		assertTrue(toTestedAccount == 2);
	}

	
}

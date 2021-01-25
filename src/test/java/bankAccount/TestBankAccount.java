package bankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestAccount {
	
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
		testAccount.withdrawAmount(500);
		assertTrue(testAccount.getBalance() == 500);
		testAccount.deposeAmount(500);
		assertTrue(testAccount.getBalance() == 1000);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawAndDeposit_Failure() {
		testAccount.withdrawAmount(-10d);
		testAccount.deposeAmount(-10d);
	}
	
	@Test
	public void testTransfer() {
		double payerBalance = payer.getBalance();
		double payeeBalance = payee.getBalance();
		payer.transferTo(payee, 500);
		assertTrue(payer.getBalance() + 500 == payerBalance);
		assertTrue(payee.getBalance() - 500 == payeeBalance);
		
	}
	
	@Test
	public void testRecordingTransfers() {
		
		payer.recordTransfers(payee, 500d);
		assertTrue(payer.getTransactionHistory().get(0).getTo().equals(payee));
		assertTrue(payee.getTransactionHistory().get(0).getFrom().equals(payer));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void recordingTransfer_Failure() {
		payer.recordTransfers(payee, -500d);
	}
	
	@Test
	public void functionnalTransferTest() {
		double amount = 100;
		payer.transferTo(payee, amount);
		
		assertTrue(payer.getBalance() == 1400);
		assertTrue(payee.getBalance() == 600);
		
		assertTrue(payer.getTransactionHistory().get(0).getTo().equals(payee));
		assertTrue(payer.getTransactionHistory().get(0).getAmount() == 100);

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

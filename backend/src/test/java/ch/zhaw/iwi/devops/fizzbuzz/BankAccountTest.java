package ch.zhaw.iwi.devops.fizzbuzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    void testInitialBalance() {
        BankAccount account = new BankAccount(100, 50);
        Assertions.assertEquals(100, account.getBalance());

    }

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount(100, 50);
        account.deposit(50);
        Assertions.assertEquals(150, account.getBalance());
    }

    @Test
    void testDepositNegative() {
        BankAccount account = new BankAccount(100, 50);
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount(100, 50);
        account.withdraw(30);
        Assertions.assertEquals(70, account.getBalance());
    }

    @Test
    void testWithdrawOverdraftAllowed() {
        BankAccount account = new BankAccount(100, 50);
        account.withdraw(120);
        Assertions.assertEquals(-20, account.getBalance());
    }

    @Test
    void testWithdrawOverdraftExceeded() {
        BankAccount account = new BankAccount(100, 50);
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdraw(200));
    }

    @Test
    void testWithdrawNegative() {
        BankAccount account = new BankAccount(100, 50);
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5));
    }

    @Test
    void testTransferSuccess() {
        BankAccount sender = new BankAccount(100, 0);
        BankAccount receiver = new BankAccount(50, 0);

        sender.transfer(receiver, 30);

        Assertions.assertEquals(70, sender.getBalance());
        Assertions.assertEquals(80, receiver.getBalance());
    }

}
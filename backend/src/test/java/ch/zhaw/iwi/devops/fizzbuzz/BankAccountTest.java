package ch.zhaw.iwi.devops.fizzbuzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    void testInitialBalance() {
        BankAccount account = new BankAccount(100, 50);
        Assertions.assertEquals(100.0, account.getBalance(), 0.0001);

    }

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount(100, 50);
        account.deposit(50);
        Assertions.assertEquals(150.0, account.getBalance(), 0.0001);
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
        Assertions.assertEquals(70.0, account.getBalance(), 0.0001);
    }

    @Test
    void testWithdrawOverdraftAllowed() {
        BankAccount account = new BankAccount(100, 50);
        account.withdraw(120);
        Assertions.assertEquals(-20.0, account.getBalance(), 0.0001);
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

        Assertions.assertEquals(70.0, sender.getBalance(), 0.0001);
        Assertions.assertEquals(80.0, receiver.getBalance(), 0.0001);
    }

}
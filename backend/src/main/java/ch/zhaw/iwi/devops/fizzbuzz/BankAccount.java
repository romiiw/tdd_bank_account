package ch.zhaw.iwi.devops.fizzbuzz;

public class BankAccount {

    private double balance;
    private double overdraftLimit;

    public BankAccount(double initialBalance, double overdraftLimit) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
        this.overdraftLimit = overdraftLimit;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (balance - amount < -overdraftLimit) {
            throw new IllegalArgumentException("Overdraft limit exceeded");
        }

        balance -= amount;
    }

public void transfer(BankAccount target, double amount) {

    if (target == null) {
        throw new IllegalArgumentException("Target account cannot be null");
    }

    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }

    this.withdraw(amount); 
    target.deposit(amount);
    }
}

package ua.itstep.shaptala.examples;

import java.util.concurrent.atomic.AtomicLong;

public class Demo3 {

    public static void main(String[] args) throws Exception {
        concurrencyRace();
    }

    // Демонстрация проблемы борьбы за ресурсы
    private static void concurrencyRace() throws InterruptedException {
        Account account = new Account(100_000);
        System.out.println("Begin balance " + account.getBalance());

        // TODO: Создать поток для снятия денег со счета
        Thread withdrawThread = null;

        // TODO: Создать поток для внесения денег на счет
        Thread depositThread = null;

        if(withdrawThread != null) {
            withdrawThread.start();
        }

        if(depositThread != null) {
            depositThread.start();
        }

        if(withdrawThread != null) {
            withdrawThread.join();
        }
        if(depositThread != null) {
            depositThread.join();
        }

        System.out.println("End balance " + account.getBalance());
    }
}

/**
 * Банковский счет
 */
class Account {
    private long balance;

    public Account(long l) {
        this.setBalance(l);
    }

    public long getBalance() {
        return balance;
    }

    private void setBalance(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) throws IllegalArgumentException {
        checkAmountNonNegative(amount);

        balance += amount;
    }

    private static void checkAmountNonNegative(long amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }

    public void withdraw(long amount) throws IllegalArgumentException {

        checkAmountNonNegative(amount);

        if (balance < amount) {
            throw new IllegalArgumentException("not enough money");
        }

        balance -= amount;
    }
}

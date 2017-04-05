package ua.itstep.shaptala.examples;

import java.util.concurrent.atomic.AtomicLong;

public class Demo3 {

    public static void main(String[] args) throws Exception {
        concurrencyRace();
    }

    private static void concurrencyRace() throws InterruptedException {
        Account account = new Account(100_000);
        System.out.println("Begin balance " + account.getBalance());

        Thread withdrawThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100_000; i++) {
                    account.withdraw(1);
                }
            }
        });

        Thread depositThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100_000; i++) {
                    account.deposit(1);
                }
            }
        });

        withdrawThread.start();
        depositThread.start();

        withdrawThread.join();
        depositThread.join();

        System.out.println("End balance " + account.getBalance());
    }
}

/**
 * Класс счет
 */
class Account {
    AtomicLong balance = new AtomicLong();

    //private long balance;

    //Object locker = new Object();

    public Account() {
        this(0L);
    }

    public Account(long l) {
        this.setBalance(l);
    }

    public long getBalance() {
        return balance.get();
    }

    private void setBalance(long balance) {
        this.balance.set(balance);
        //this.balance = balance;
    }

    //public synchronized void deposit(long amount) throws IllegalArgumentException {
    public void deposit(long amount) throws IllegalArgumentException {
        checkAmountNonNegative(amount);

        //synchronized (this) {
            //balance += amount;
            balance.addAndGet(amount);
        //}
    }

    private static void checkAmountNonNegative(long amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }

    //public synchronized void withdraw(long amount) throws IllegalArgumentException {
    public void withdraw(long amount) throws IllegalArgumentException {
        //synchronized (this) {
        checkAmountNonNegative(amount);

        if (balance.get() < amount) {
            throw new IllegalArgumentException("not enough money");
        }

            //balance -= amount;
            balance.addAndGet(-amount);
        //}
    }
}

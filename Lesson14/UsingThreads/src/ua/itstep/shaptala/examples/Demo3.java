package ua.itstep.shaptala.examples;

public class Demo3 {

    public static void main(String[] args) throws Exception {
        concurrencyRace();
    }

    private static void concurrencyRace() throws InterruptedException {
        Account account = new Account(100_000);
        System.out.println("Begin balance " + account.getBalance());

        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 20_000; i++) {
                account.withdraw(1);
            }
        });

        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 20_000; i++) {
                account.deposit(1);
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
    private long balance;

    public Account() {
        this(0L);
    }

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

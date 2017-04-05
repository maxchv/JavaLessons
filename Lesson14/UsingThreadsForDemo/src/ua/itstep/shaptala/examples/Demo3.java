package ua.itstep.shaptala.examples;

public class Demo3 {


    public static void main(String[] args) throws Exception {
        concurrencyRaceRunnable();
    }

    private static void concurrencyRaceRunnable() throws InterruptedException {
        Account account = new Account(100_000);
        System.out.println("Begin balance " + account.getBalance());

        Runnable withdrawRunnable = null;
        Thread withdrawThread = new Thread(withdrawRunnable);
        Runnable depositRunnable = null;
        Thread depositThread = new Thread(depositRunnable);

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
    private Long balance;

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

    public void deposit(long amount) {
        checkAmountNonNegative(amount);

        balance += amount;
    }

    private static void checkAmountNonNegative(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }

    public void withdraw(long amount) {
        checkAmountNonNegative(amount);

        if (balance < amount) {
            throw new IllegalArgumentException("not enough money");
        }

        balance -= amount;
    }
}

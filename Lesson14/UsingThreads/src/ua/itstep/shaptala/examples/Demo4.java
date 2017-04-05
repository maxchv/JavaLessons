package ua.itstep.shaptala.examples;

public class Demo4 {

    public static void main(String[] args) throws InterruptedException {
        Account2 account = new Account2(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50_000_000; i++) {
                    account.deposit(1);
                }
            }
        }).start();

        //account.withdraw(50_000_000);

        /* TODO: вызвать метод waitAndWithdraw()*/
        account.waitAndWithdraw(50_000_000);
        System.out.println("Calling waitAndWithdraw()...");

        System.out.println("waitAntWithdraw() finished " + account.getBalance());
    }
}

class Account2 {
    private long balance;

    public Account2() {
        this(0L);
    }

    public Account2(long l) {
        this.setBalance(l);
    }

    public long getBalance() {
        return balance;
    }

    private void setBalance(long balance) {
        this.balance = balance;
    }

    public synchronized void deposit(long amount) {
        checkAmountNonNegative(amount);

        balance += amount;
        notifyAll(); // уведомить поток о изменении данных
    }

    private static void checkAmountNonNegative(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }

    public synchronized void waitAndWithdraw(long amount) throws InterruptedException {
        /* TODO: Проверить достаточно ли денег */
        while (balance < amount) {
            wait(); // ожидать уведомления из другого потока
        }
        withdraw(amount);
    }

    public synchronized void withdraw(long amount) {
        checkAmountNonNegative(amount);

        if (balance < amount) {
            throw new IllegalArgumentException("not enough money");
        }

        balance -= amount;
    }
}
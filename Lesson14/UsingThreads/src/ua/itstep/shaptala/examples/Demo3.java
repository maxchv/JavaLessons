package ua.itstep.shaptala.examples;

public class Demo3 {

	private static class DepositThread extends Thread {
		private final Account account;

		public DepositThread(Account account) {
			this.account = account;
		}

		@Override
		public void run() {
			for (int i = 0; i < 20_000; i++) {
				account.deposit(1);
			}
		}
	}

	private static class WithdrawThread extends Thread {
		private final Account account;

		public WithdrawThread(Account account) {
			this.account = account;
		}

		@Override
		public void run() {

			for (int i = 0; i < 20_000; i++) {
				account.withdraw(1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		 concurrencyRace();
		//concurrencyRaceRunnable();
	}

	private static void concurrencyRaceRunnable() throws InterruptedException {
		Account account = new Account(100_000);
		System.out.println("Begin balance " + account.getBalance());

		Runnable withdrawRunnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 20_000; i++) {
					account.withdraw(1);
				}
			}
		};
		Thread withdrawThread = new Thread(withdrawRunnable);
		Runnable depositRunnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 20_000; i++) {
					account.deposit(1);
				}
			}
		};
		Thread depositThread = new Thread(depositRunnable);

		withdrawThread.start();
		depositThread.start();

		withdrawThread.join();
		depositThread.join();

		System.out.println("End balance " + account.getBalance());
	}

	private static void concurrencyRace() throws InterruptedException {
		Account account = new Account(100_000);
		System.out.println("Begin balance " + account.getBalance());

		Thread withdrawThread = new WithdrawThread(account);
		Thread depositThread = new DepositThread(account);

		withdrawThread.start();
		depositThread.start();

		withdrawThread.join();
		depositThread.join();

		System.out.println("End balance " + account.getBalance());
	}

}

/**
 * 
 * Класс счет
 *
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

	public synchronized void deposit(long amount) {
		checkAmountNonNegative(amount);

		balance += amount;
	}

	private static void checkAmountNonNegative(long amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("negative amount");
		}
	}

	public synchronized void withdraw(long amount) {
		checkAmountNonNegative(amount);

		if (balance < amount) {
			throw new IllegalArgumentException("not enough money");
		}

		balance -= amount;
	}
}

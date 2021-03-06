package ua.itstep.shaptala.examples;

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
	}

	private static void checkAmountNonNegative(long amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("negative amount");
		}
	}

	public void waitAndWithdraw(long amount) throws InterruptedException {
		checkAmountNonNegative(amount);
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

public class WaitAndNotifyDemo4 {

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

		System.out.println("Calling waitAndWithdraw()...");
		// TODO: Get money
		account.waitAndWithdraw(50_000_000);

		System.out.println("waitAntWithdraw() finished " + account.getBalance());
	}

}

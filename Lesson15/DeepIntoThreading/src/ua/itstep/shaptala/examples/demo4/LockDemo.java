package ua.itstep.shaptala.examples.demo4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

	public static void main(String[] args) throws Exception {
		Account account = new Account(0);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<60_000_000; i++) {
					account.deposit(1);
				}				
			}
		}).start();
		
		System.out.println("Entering waitAndWithdraw");
		
		account.waitAndWithdraw(50_000_000);
		
		System.out.println("waitAndWithdraw finished, end balance = " + account.getBalance());
	}
}

class Account {
	private final Lock lock = new ReentrantLock();
	private final Condition balanceIncrease = lock.newCondition();

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

	// public synchronized void deposit(long amount) {
	public void deposit(long amount) {
		checkAmountNonNegative(amount);
		lock.lock();

		// synchronized (this) {
		try {
			balance += amount;
			balanceIncrease.signalAll();
			// notifyAll();
		} finally {
			lock.unlock();
		}
	}

	private static void checkAmountNonNegative(long amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("negative amount");
		}
	}

	// public synchronized void waitAndWithdraw(long amount) throws
	// InterruptedException {
	public void waitAndWithdraw(long amount) throws InterruptedException {
		checkAmountNonNegative(amount);
		lock.lock();
		try {
			while (balance < amount) {
				// wait();
				balanceIncrease.await();
			}
			balance -= amount;
		} finally {
			lock.unlock();
		}
	}

	// public synchronized void withdraw(long amount) {
	public void withdraw(long amount) {
		checkAmountNonNegative(amount);
		lock.lock();
		try {
			if (balance < amount) {
				throw new IllegalArgumentException("not enough money");
			}
			balance -= amount;
		} finally {
			lock.unlock();
		}
	}
}

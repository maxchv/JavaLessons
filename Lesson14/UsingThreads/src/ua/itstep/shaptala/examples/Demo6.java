package ua.itstep.shaptala.examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by User on 12.03.2017.
 */
public class Demo6 {
    static class Player extends Thread {
        CountDownLatch countDownLatch;
        public Player(String name, CountDownLatch countDownLatch) {
            super(name);
            this.countDownLatch = countDownLatch;
            start();
        }

        @Override
        public void run() {
            System.out.println("Player " + getName() + " is ready");
            countDownLatch.countDown();
            try {
                countDownLatch.await();
                System.out.println(getName() + " is starting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        Player p1 = new Player("one", countDownLatch);
        Player p2 = new Player("two", countDownLatch);
        Player p3 = new Player("tree", countDownLatch);
        Player p4 = new Player("four", countDownLatch);
        Player p5 = new Player("five", countDownLatch);
    }
}

package ua.itstep.shaptala.examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by User on 12.03.2017.
 */
public class Demo6 {
    static class Player extends Thread {

        //CyclicBarrier cyclicBarrier;
        //CountDownLatch countDownLatch;
        Semaphore semaphore;

        public Player(String name, Semaphore semaphore) {
            super(name);
            //this.countDownLatch = countDownLatch;
            this.semaphore = semaphore;
            start();
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Player " + getName() + " is ready");
            //System.out.println("Await: " + cyclicBarrier.getNumberWaiting());
//            try {
//                cyclicBarrier.await();
//            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
//            }
            //countDownLatch.countDown();
//            System.out.println(countDownLatch.getCount());
//            try {
//                countDownLatch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
            System.out.println(getName() + " is starting");

        }
    }

    public static void main(String[] args) {
        //CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        //CountDownLatch countDownLatch = new CountDownLatch(4);
        Semaphore semaphore = new Semaphore(2);
        Player p1 = new Player("one", semaphore);
        Player p2 = new Player("two", semaphore);
        Player p3 = new Player("tree", semaphore);
        Player p4 = new Player("four", semaphore);
        Player p5 = new Player("five", semaphore);
    }
}

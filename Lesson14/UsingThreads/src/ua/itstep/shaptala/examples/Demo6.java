package ua.itstep.shaptala.examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Demo6 {

    static class Player extends Thread {

        public Player(String name) {
            super(name);
            start();
        }

        @Override
        public void run() {
            System.out.println("Player " + getName() + " is ready");

            System.out.println(getName() + " is starting");
        }
    }

    // Synchronization demo
    public static void main(String[] args) {
        new Player("one");
        new Player("two");
        new Player("three");
        new Player("four");
        new Player("five");
        new Player("six");
    }
}

package ua.itstep.shaptala.examples;

import java.time.LocalTime;
import java.util.concurrent.*;

public class Demo2 {

    public static void main(String[] args) throws Exception {
        /* TODO: Создать экземпляр класса WorkedThread */
        WorkedThread workedThread = new WorkedThread();

        /* TODO: Создать экземпляр класса SleeperThread */
        SleeperThread sleeperThread = new SleeperThread();

        /* TODO: демоны??? */
        sleeperThread.setDaemon(true);
        workedThread.setDaemon(true);

        /* TODO: Запустить потоки */
        System.out.println("Starting threads at " + LocalTime.now());
        sleeperThread.start();
        workedThread.start();

        Thread.sleep(100L);

        /* TODO: Прервать потоки */
		// System.out.println("Interrupting threads at " + LocalTime.now());
        // sleeperThread.interrupt();// прервать работу спящего потока
        // workedThread.interrupt();

		/* TODO: Присоединить потоки */
//		System.out.println("Joining threads");
//        workedThread.join();
//        sleeperThread.join();

        System.out.println(Thread.currentThread().getName() + " thread done at " + LocalTime.now());
    }

    private static class WorkedThread extends Thread {

        public WorkedThread() {
            super(WorkedThread.class.getSimpleName());
        }

        @Override
        public void run() {
            long sum = 0;
            System.out.println(getName() + " run at: " + LocalTime.now());
            for (int i = 0; i < 2_000_000_000; i++) {
                sum += i;
                /* TODO: Проверить не прерван ли поток */
                if(isInterrupted()) { // Thread.interrupted()
                    System.out.println("Loop interrupted at i = " + i);
                    break;
                }
            }
            System.out.println("End loop for " + getName() + " at: " + LocalTime.now());
        }
    }

    private static class SleeperThread extends Thread {

        public SleeperThread() {
            super(SleeperThread.class.getSimpleName());
        }

        @Override
        public void run() {
            System.out.println(getName() + " run at: " + LocalTime.now());
            /* TODO: Усыпить на 1000 мс */
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                //e.printStackTrace();
            }

            System.out.println("End " + getName() + " at: " + LocalTime.now());
        }
    }
}

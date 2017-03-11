package ua.itstep.shaptala.examples;

import java.time.LocalTime;
import java.util.concurrent.*;

public class Demo2 {

    public static void main(String[] args) throws Exception {
        /* TODO: Создать экземпляр класса WorkedThread */

        /* TODO: Создать экземпляр класса SleeperThread */

        /* TODO: демоны??? */

        /* TODO: Запустить потоки */
        System.out.println("Starting threads at " + LocalTime.now());

        Thread.sleep(100L);

        /* TODO: Прервать потоки */
		System.out.println("Interrupting threads at " + LocalTime.now());

		/* TODO: Присоединить потоки */
		// System.out.println("Joining threads");

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
                //System.out.println("Loop interrupted at i = " + i);
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
            //System.out.println("Sleep interrupted");
            System.out.println("End " + getName() + " at: " + LocalTime.now());
        }
    }
}

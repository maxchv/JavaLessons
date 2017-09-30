package ua.itstep.shaptala.examples;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by max on 01.10.2017.
 */
public class Demo8 {
    // TODO: implements interface Callable instead Runnable
    public static class Worker implements Runnable {

        final String name;


        public Worker(String string) {
            name = string;
        }

        @Override
        public void run() {
            long sleepTime = (long) (Math.random()*10000L);
            System.out.println(name + " started, going to sleep for " + sleepTime + " seconds");
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " finished");
            // FIXME: return name???
            // return name;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // TODO: ExecutorService, Executors
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println("Submit worker 1");
        Future<String> future1 = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Worker 1";
        });

        System.out.println("Submit worker 1");

        System.out.println("Result of worker 1: " + future1.get());
        System.out.println("Result of worker 2: ");
        System.out.println("---------------------");

        System.out.println("Submit workers using invoke All");

        List<Worker> workerList =  Arrays.asList(
                        new Worker("Worker 3"),
                        new Worker("Worker 4"),
                        new Worker("Worker 5")
        );

        System.out.println("Exited invokeAll");

        // for all tasks {
            System.out.println("Result from worker: ");
        //}

        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}

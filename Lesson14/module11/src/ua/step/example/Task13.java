package ua.step.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * Callable
 *
 */
public class Task13
{
    public static void main(String[] args) throws InterruptedException,
            ExecutionException
    {
        Random rnd = new Random();
        int n = 100;
        short[][] data = new short[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                data[i][j] = (short) rnd.nextInt(Short.MAX_VALUE);
            }
        }
        System.out.println(Arrays.deepToString(data));
        calculate(data);
        thredCalculate(data);

    }

    private static void calculate(short[][] data)
    {
        long start = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < data.length; i++)
        {
            for (int j = 0; j < data[i].length; j++)
            {
                sum += data[i][j];
            }
        }
        System.out.println(sum);
        System.out.println("Work time = " + (System.nanoTime() - start));

    }

    public static void thredCalculate(short[][] data) throws InterruptedException, ExecutionException
    {
        long start = System.nanoTime();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime
                .getRuntime().availableProcessors());
        Queue<Future<Integer>> features = new LinkedList<Future<Integer>>();
        for (int i = 0; i < data.length; i++)
        {
            Callable<Integer> calculator = new RowCalculator(data[i]);
            Future<Integer> feature = pool.submit(calculator);
            features.add(feature);
        }
        long sum = 0;
        while (!features.isEmpty())
        {
            Future<Integer> feature = features.remove();
            if (feature.isDone())
            {
                sum += feature.get();
            }
            else
            {
                features.add(feature);
            }
        }
        System.out.println(sum);
        System.out.println("thread");
        System.out.println("Work time = " + (System.nanoTime() - start));
    }
}

class RowCalculator implements Callable<Integer>
{
    short[] data;

    RowCalculator(short[] data)
    {
        this.data = data;
    }

    @Override
    public Integer call() throws Exception
    {
        int summa = 0;
        for (int i = 0; i < data.length; i++)
        {
            summa += data[i];
        }
        return summa;
    }
}

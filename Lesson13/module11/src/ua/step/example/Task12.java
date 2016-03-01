package ua.step.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Запустите тест несколько раз и определить порядок выхода потоков из состояния
 * wait
 * 
 */
public class Task12
{
    static final DateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args)
    {
        Object sync = new Object();
        for (int i = 0; i < 5; i++)
        {
            Thread t = new Thread(new WaitingThread(sync));
            t.start();
        }
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            System.err.println("main::  Прерван: " + ex.getMessage());
        }
        synchronized (sync)
        {
            log("main::  Вызов notifyAll");
            sync.notifyAll();
            log("main::  Уснул на  3 сек");
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException ex)
            {
                System.err.println("main::  Прерван: " + ex.getMessage());
            }
            log("main::  Выход из блока синхранизации");
        }
    }

    static void log(String msg)
    {
        System.out.println(FORMAT.format(new Date()) + ": " + msg);
    }

    static class WaitingThread implements Runnable
    {
        static int nextId = 1;

        private Object sync;

        private int id;

        public WaitingThread(Object sync)
        {
            this.sync = sync;
            id = nextId++;
        }

        public void run()
        {
            synchronized (sync)
            {
                log("own(" + id + ")::Ждет");
                try
                {
                    sync.wait();
                }
                catch (InterruptedException ex)
                {
                    log("own(" + id + ")::Прерван: " + ex.getMessage());
                }
                log("own(" + id + ")::Запушен снова");
                log("own(" + id + ")::Заснул на 1 сек");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex)
                {
                    log("own(" + id + ")::Прерван: " + ex.getMessage());
                }
                log("own(" + id + ")::Закончил работу");
            }
        }
    }
}

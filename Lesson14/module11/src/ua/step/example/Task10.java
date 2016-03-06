package ua.step.example;

public class Task10
{
    public static void main(String[] args)
    {
        Object sync = new Object();
        Thread t = new Thread(new WaitingThread(sync));
        t.start();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            System.err.println("main::Прерван: " + ex.getMessage());
        }
        synchronized (sync)
        {
            System.out.println("main::Вызываем notify");
            sync.notify();
            System.out.println("main:: Ждем 5 сек");
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException ex)
            {
                System.err.println("main::Прерван: " + ex.getMessage());
            }
            System.out.println("main::Выход из синхронизированного блока");
        }
    }

    static class WaitingThread implements Runnable
    {
        private Object sync;

        public WaitingThread(Object sync)
        {
            this.sync = sync;
        }

        public void run()
        {
            synchronized (sync)
            {
                System.out.println("own:: Ждем");
                try
                {
                    sync.wait();
                }
                catch (InterruptedException ex)
                {
                    System.err.println("own:: Прерван: " + ex.getMessage());
                }
                System.out.println("own:: Запушен снова");
            }
        }
    }
}
/**
 * несмотря на то, что notify уже вызван, ждущий поток все еще стоит до того
 * момента, пока не будет отпущен монитор. Это происходит при выходе из
 * синхронизированного блока в потоке, вызвавшем notify. Сразу после этого
 * ждущий поток захватывает монитор обратно и продолжает работу.
 */

package ua.step.example;

/**
 * 
 * Условием для продолжения работы потока в WaitingThread является
 * data.value!=0. Если это условие не выполняется – поток ждет. Причем СНАЧАЛА
 * он получает монитор объекта, на котором идет синхронизация – sync, – а уж
 * потом проверяет условие. Это сделано для синхронизации данных, а именно –
 * значения data.value. Далее вызывается sync.wait(). Основной же поток
 * задерживается на небольшое время, чтобы дать второму потоку стартовать, после
 * чего выставляет нужное значение (перед этим захватив монитор!) и вызывает
 * sync.notify(). После этого второй поток продолжает работу и, как мы видим, у
 * него значение data.value = 1.
 */
public class Task09
{
    public static void main(String[] args)
    {
        Object sync = new Object();
        Data data = new Data();
        Thread t = new Thread(new WaitingThread(sync, data));
        t.start();
        try
        {
            System.out.println("main::Заснул");
            Thread.sleep(500);
        }
        catch (InterruptedException ex)
        {
            System.err.println("main::Прерван: " + ex.getMessage());
        }
        synchronized (sync)
        {
            System.out.println("main::установка значения  value в 1");
            data.value = 1;
            System.out.println("main:: будет вызван notify");
            sync.notify();
            System.out.println("main:: поток notified");
        }
    }

    static class Data
    {
        public int value = 0;
    }

    static class WaitingThread implements Runnable
    {
        private Object sync;

        private Data data;

        public WaitingThread(Object sync, Data data)
        {
            this.sync = sync;
            this.data = data;
        }

        public void run()
        {
            System.out.println("own:: Thread запушен");
            synchronized (sync)
            {
                if (data.value == 0)
                {
                    try
                    {
                        System.out.println("own:: Ждет");
                        sync.wait();
                        System.out.println("own:: Запушен снова");
                    }
                    catch (InterruptedException ex)
                    {
                        System.err.println("own:: Прерван: "
                                + ex.getMessage());
                    }
                }
                System.out.println("own:: data.value = " + data.value);
            }
        }
    }
}
/**
 * Если поток останавливается внутри метода wait, то системный монитор
 * отпускается! В противном случае будет невозможно вызвать notify.
 */

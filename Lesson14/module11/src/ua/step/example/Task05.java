package ua.step.example;

/**
 * 
 * Прерывание работы потока
 *
 */
public class Task05
{
    public static void main(String[] args)
    {
        MyThred thred = new MyThred("A");
        thred.start();
        try
        {
            Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thred.interrupt();
        System.out.println("End main");
    }

    private static class MyThred extends Thread
    {
        private final String name;

        public MyThred(String name)
        {
            this.name = name;
        }

        @Override
        public void run()
        {
            for (int i = 0; i <= 100; i++)
            {
                if (Thread.currentThread().isInterrupted())
                {
                    System.out.println("Thread " + name + " interapted");
                    break;
                }
                System.out.println(name + " " + i);
            }
        }
    }
}

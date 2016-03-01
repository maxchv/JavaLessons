package ua.step.example;

/**
 * 
 * Присоединение (ожидание) потоков 
 *
 */
public class Task06
{
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        Sleeper sleepy1 = new Sleeper("Sleepy 1", 1500);
        Sleeper sleepy2 = new Sleeper("Sleepy 2", 2000);
        Joiner joiner1 = new Joiner("Joiner 1", sleepy1);
        Joiner joiner2 = new Joiner("Joiner 2", sleepy2);
        //sleepy1.interrupt();
        //joiner2.getThread().interrupt();
    }
}

class Sleeper extends Thread
{
    private int duration;

    public Sleeper(String name, int sleepTime)
    {
        super(name);
        duration = sleepTime;
        start();
    }

    public void run()
    {
        try
        {
            sleep(duration);
        }
        catch (InterruptedException e)
        {
            System.out.println(getName() + " прерван");
            return;
        }
        System.out.println(getName() + " активизировался.");
    }
}

class Joiner implements Runnable
{
    private Thread sleeper;

    private Thread thread;

    public Joiner(String name, Thread sleeper)
    {
        this.sleeper = sleeper;
        thread = new Thread(this);
        thread.setName(name);
        thread.start();
    }

    public void run()
    {
        try
        {
            sleeper.join();
            System.out.println(thread.getName() + " завершен.");
        }
        catch (InterruptedException e)
        {
            System.out.println(thread.getName() + " прерван.");
        }
    }

    public Thread getThread()
    {
        return thread;
    }
}
package ua.step.example;

public class Task02
{
    public static void main(String[] args)
    {
        MyThred thred = new MyThred("A");
        thred.run(); //!!!
        MyThred thred1 = new MyThred("B");
        thred1.start();
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
            System.out.printf("Поток %s начал работу\n", name);
        	for (int i = 0; i < 100; i++)
            {
                System.out.println(name + " " + i);
            }
        	System.out.printf("Поток %s закончил работу\n", name);
        }
    }
}
package ua.step.example;

/*
 * Потоки (нити)
 */
public class Task01
{
    public static void main(String[] args)
    {
    	System.out.println("Главный поток начал работу");
    	MyThred myThred = new MyThred("A");
        Thread thread = new Thread(myThred);
        thread.start();
        System.out.println("Главный поток закончил работу");
        //FIXME добавить еще один поток с другим именем
        //thread.start(); !!!
    }

    private static class MyThred implements Runnable
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
        	for (int i =0; i < 100; i++)
            {
                System.out.println(name + " " + i);
            }
        	System.out.printf("Поток %s закончил работу\n", name);
        }
    }
}

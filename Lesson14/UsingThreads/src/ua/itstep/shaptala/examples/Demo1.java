package ua.itstep.shaptala.examples;

public class Demo1 {

    /* TODO: Реализация интерфейса Runnable */
    static class RunnableThread implements Runnable {

        String name;

        public RunnableThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
//            System.out.println(Thread.currentThread().getId());
//            System.out.println(Thread.currentThread().getName());
            System.out.println(name);
        }
    }

    /* TODO: Наследование класса Thread */
    static class ExtendsThread extends Thread {

        public ExtendsThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(getName());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new RunnableThread("RunnableThread #" + i));
            t1.start();

            ExtendsThread t2 = new ExtendsThread("ExtendsThread #"+i);
            t2.start();

            final int num = i;
           /* TODO: Анонимный класс реализующий интерфейс Runnable */
           new Thread(new Runnable() {
               @Override
               public void run() {
                   System.out.println("Anonymous thread #" + num);
               }
           }).start();

           /* TODO: Анонимный класс налседующий класс Thread */
        }
        System.out.println("Hello from main thread");
    }
}

package ua.itstep.shaptala.examples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo5 {
	
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }

        public synchronized void seyHi(Friend friend) {
            System.out.format("%s: %s"
                + " said hi to me!%n",
                name, friend.getName());
            friend.hiBack(this);
        }
        public synchronized void hiBack(Friend friend) {
            System.out.format("%s: %s"
                + " has said hi back to me!%n",
                name, friend.getName());
        }
    }

    public static void main(String[] args) {
        final Friend petro = new Friend("Petro");
        final Friend vano =  new Friend("Vano");
        
        new Thread(() -> petro.seyHi(vano)).start();
        
        new Thread(() -> vano.seyHi(petro)).start();
    }
}
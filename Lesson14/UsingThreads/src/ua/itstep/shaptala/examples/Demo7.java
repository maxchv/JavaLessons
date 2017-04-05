package ua.itstep.shaptala.examples;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Demo7 {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1,2,3,4,5));//Arrays.asList(1,2,3,4,5);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next()+1);
        }
        System.out.println(list);
    }
}

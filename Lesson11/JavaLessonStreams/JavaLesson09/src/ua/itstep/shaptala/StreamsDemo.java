package ua.itstep.shaptala;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamsDemo {

    static <T> void consumer(T inp) {
        System.out.println(inp);
    }

    static void directStream() {
        System.out.format("Stream%n------%n");
        int[] arr = {1, 2, 3, 4, 5, 5, 2, 1};
        String str = "Hello";
        for (int item : arr) {
            System.out.println("item = " + item);
        }     

//        IntStream.of(1,2,34);
//        LongStream;
//        DoubleStream

    }

    static List<String> stringCollection = new ArrayList<>();

    static {
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
    }

    static void collectionStream() {
        System.out.format("Collection Stream%n------%n");
//        for (String item : stringCollection) {
//            System.out.println("item = " + item);
//        }
      
    }

    private static void filterExample() {
        System.out.format("Filter%n------%n");
        // print string starts with 'a'

//        List<String> res = new ArrayList<>();
//        for (String item : stringCollection) {
//            if (item.startsWith("a")) {
//                res.add(item);
//            }
//        }
//        for (String item : res) {
//            System.out.println("item = " + item);
//        }

     
    }

    private static void sortExample() {
        System.out.format("%nSorted%n------%n");
        // sort string starts with 'a' and print
//        List<String> res = new ArrayList<>();
//        for (String item : stringCollection) {
//            if (item.startsWith("a")) {
//                res.add(item);
//            }
//        }
//        res.sort((a, b) -> a.compareTo(b));
//        for (String item : res) {
//            System.out.println("item = " + item);
//        }
       
    }

    private static List<Integer> mapExample() {
        System.out.format("%nMap%n---%n");
        // find string length 4 chars, get last char, convert to int, get list
//        List<Integer> intCollection = new ArrayList<>();
//
//        for (String item : stringCollection) {
//            if (item.length() == 4) {
//                intCollection.add(Integer.valueOf(item.substring(3)));
//            }
//        }

        return null;
    }

    private static void distinctExample(List<? extends Integer> intCollection) {
        System.out.format("%nDistinct%n--------%n");
//        List<Integer> distinctList = new ArrayList<>();
//        for (Integer item : intCollection) {
//            if (!distinctList.contains(item)) {
//                distinctList.add(item);
//            }
//        }
        
    }

    private static void matchExample() {
        System.out.format("%nMatch%n-----%n");

//        for (String item : stringCollection) {
//            if (item.startsWith("a")) {
//                isAnyWordStartA = true;
//                break;
//            }
//        }
//        System.out.println("isAnyWordStartA: " + isAnyWordStartA);


//        for (String item : stringCollection) {
//            if (!item.startsWith("a")) {
//                isAllWordStartA = false;
//                break;
//            }
//        }
//        System.out.println("isAllWordStartA: " + isAllWordStartA);



//        System.out.println("isNoneWordStartA: " + isNoneWordStartA);
    }

    private static void minMaxExample(List<Integer> intCollection) {
        System.out.format("%nMin/Max%n-------%n");

//        Integer maxInt = intCollection.get(0);
//        for(Integer item: intCollection) {
//            if(Integer.compare(item, maxInt) > 0) {
//                maxInt = item;
//            }
//        }

//        System.out.println("max: " + maxInt);

//        Integer minInt = intCollection.get(0);
//        for(Integer item: intCollection) {
//            if(Integer.compare(item, minInt) < 0) {
//                minInt = item;
//            }
//        }

//       System.out.println("min: " + minInt);
    }

    private static void countExample(List<Integer> intCollection) {
        System.out.format("%nCount%n-----%n");
        // count only even number

//        for(Integer item: intCollection) {
//            if(item % 2 == 0) {
//                countEven++;
//            }
//        }

//        System.out.println("count: " + countEven);
    }

    private static void averageExample(List<Integer> intCollection) {
        System.out.format("%nAverage%n-------%n");
//        double average = 0;
//        for(Integer item: intCollection) {
//            average += item;
//        }
//        average /= intCollection.size();

		// summaryStatistics
    }

    private static void reduceExample(List<Integer> intCollection) {
        System.out.format("%nReduce%n-------%n");
//        int sum = 0;
//        for(Integer item: intCollection) {
//            sum = sum + item;
//        }
//        System.out.println("sum: " + sum);
    }

    public static void main(String[] args) {

        directStream();

        collectionStream();

        // --- Intermediate operations ---
        // Filter
        filterExample();

        // Sorted
        sortExample();

        // Map
        List<Integer> intCollection = mapExample();

        // Distinct
        distinctExample(intCollection);

        // --- Terminal operations ---
        // Match
        matchExample();

        // Max, Min
        minMaxExample(intCollection);

        // Count
        countExample(intCollection);

        // Average	(terminal operation)
        averageExample(intCollection);

        // Reduce (terminal operation)
        reduceExample(intCollection);

        // Parallel Streams
        parallelStreamsExample();
    }

    private static void parallelStreamsExample() {
        int max = 300_000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        System.out.format("%nSequence%n-------%n");
        List<String> cpy = new ArrayList<>(values);
        Instant start = Instant.now();
        cpy.stream()
                .sorted()
                .count();
        Instant end = Instant.now();
        long seq = Duration.between(start, end).getNano();
        System.out.println("seq: " + seq);

        System.out.format("%nParallel%n-------%n");
        start = Instant.now();
        values.parallelStream()
                .sorted()
                .count();
        end = Instant.now();
        long par = Duration.between(start, end).getNano();
        System.out.println("\npar: " + par);
        System.out.println("seq/par: " + ((double) seq / par));
    }
}

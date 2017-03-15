package org.itstep;

public class MapDemo {
    public static void main(String[] args) {
        Map map = new ArrayMap();
        map.put("one", "two");
        map.put(1, "один");
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));
        System.out.println(map.get(1));
    }
}






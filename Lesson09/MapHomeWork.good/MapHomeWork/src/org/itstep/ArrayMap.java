package org.itstep;

public class ArrayMap implements Map {

    private int count;
    private int initSize = 10;
    private Pair[] map;

    public static class Pair implements Map.Entry {

        Object key;
        Object value;

        public Pair(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            return value;
        }
    }

    public ArrayMap() {
        clear();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Pair entry = getPair(key);
        return entry == null;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean contains = false;
        for (int i=0; i<count; i++) {
            if (map[i].getValue().equals(value)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public Object get(Object key) {
        Pair entry = getPair(key);
        Object value = null;
        if(entry != null) {
            value = entry.getValue();
        }
        return value;
    }

    @Override
    public Object put(Object key, Object value) {
        Pair old = getPair(key);
        if(old == null) {
            Pair item = new Pair(key, value);
            if (count >= map.length) {
                resizeMap();
            }
            map[count++] = item;
            return null;
        } else {
            Object oldValue = old.getValue();
            old.setValue(value);
            return oldValue;
        }
    }

    private Pair getPair(Object key) {
        Pair entry = null;
        for (int i=0; i<count; i++) {
            if (map[i].getKey().equals(key)) {
                entry = map[i];
                break;
            }
        }
        return entry;
    }

    private void resizeMap() {
        resizeMap(map.length + 10);
    }

    private void resizeMap(int newSize) {
        if(newSize > map.length) {
            Pair[] tmp = new Pair[newSize];
            for (int i = 0; i < map.length; i++) {
                tmp[i] = map[i];
            }
            map = tmp;
        }
    }

    @Override
    public Object remove(Object key) {
        int idx = -1;
        Object value = null;
        for(int i=0; i<map.length; i++) {
            if(map[i].getKey().equals(key)) {
                idx = i;
                value = map[i].getValue();
                break;
            }
        }
        if(idx != -1) {
            for (int i=idx; i<map.length; i++) {
                map[i] = map[i+1];
            }
        }

        return value;
    }

    @Override
    public void clear() {
        map = new Pair[initSize];
        count = 0;
    }
}
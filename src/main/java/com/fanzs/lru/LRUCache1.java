package com.fanzs.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache1<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    public LRUCache1(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache1<Integer, String> lruCache1 = new LRUCache1<>(3);
        lruCache1.put(1, "1");
        lruCache1.put(2, "2");
        lruCache1.put(3, "3");
        System.out.println(lruCache1.keySet());

        lruCache1.put(4, "4");
        System.out.println(lruCache1.keySet());

        lruCache1.put(5, "5");
        System.out.println(lruCache1.keySet());

        lruCache1.put(3, "3");
        System.out.println(lruCache1.keySet());

        lruCache1.put(3, "3");
        System.out.println(lruCache1.keySet());

        lruCache1.put(3, "3");
        System.out.println(lruCache1.keySet());
    }
}

package com.fanzs.b.pointer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Staticistic {

    private static volatile ConcurrentHashMap<String, AtomicInteger> ipCountMap = new ConcurrentHashMap<String, AtomicInteger>();

//    private static final ReentrantLock lock = new ReentrantLock();

    public static int access(String ip) {
        AtomicInteger countAtomicInteger = ipCountMap.getOrDefault(ip, new AtomicInteger(0));
        countAtomicInteger.incrementAndGet();
        ipCountMap.put(ip, countAtomicInteger);
        return countAtomicInteger.get();
    }
}

package com.fanzs.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadUnsafe {

    public static void main(String[] args) {
        threadSafeList();
    }

    /**
     * Exception in thread "99" java.util.ConcurrentModificationException
     * 	at java.util.LinkedList$ListItr.checkForComodification(LinkedList.java:966)
     * 	at java.util.LinkedList$ListItr.next(LinkedList.java:888)
     * 	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
     * 	at java.lang.String.valueOf(String.java:2994)
     * 	at java.io.PrintStream.println(PrintStream.java:821)
     * 	at com.fanzs.basic.ThreadUnsafe.lambda$unsafeList$0(ThreadUnsafe.java:24)
     */
    public static void unsafeList(){
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        System.out.println(list.size());
    }

    /**
     * public boolean add(E e) {
     *         final ReentrantLock lock = this.lock;
     *         lock.lock();
     *         try {
     *             Object[] elements = getArray();
     *             int len = elements.length;
     *             Object[] newElements = Arrays.copyOf(elements, len + 1);
     *             newElements[len] = e;
     *             setArray(newElements);
     *             return true;
     *         } finally {
     *             lock.unlock();
     *         }
     *     }
     */
    public static void threadSafeList(){
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        System.out.println(list.size());
    }

    /**
     * Exception in thread "43" java.util.ConcurrentModificationException
     * 	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1442)
     * 	at java.util.HashMap$KeyIterator.next(HashMap.java:1466)
     * 	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
     * 	at java.lang.String.valueOf(String.java:2994)
     * 	at java.io.PrintStream.println(PrintStream.java:821)
     * 	at com.fanzs.basic.ThreadUnsafe.lambda$main$0(ThreadUnsafe.java:16)
     * 	at java.lang.Thread.run(Thread.java:748)
     * Exception in thread "5" java.util.ConcurrentModificationException
     * 	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1442)
     * 	at java.util.HashMap$KeyIterator.next(HashMap.java:1466)
     * 	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
     * 	at java.lang.String.valueOf(String.java:2994)
     */
    public static void unsafeSet(){
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
        System.out.println(set.size());
    }

    /**
     *java.util.ConcurrentModificationException
     * 	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1442)
     * 	at java.util.HashMap$EntryIterator.next(HashMap.java:1476)
     * 	at java.util.HashMap$EntryIterator.next(HashMap.java:1474)
     * 	at java.util.AbstractMap.toString(AbstractMap.java:554)
     * 	at java.lang.String.valueOf(String.java:2994)
     * 	at java.io.PrintStream.println(PrintStream.java:821)
     * 	at com.fanzs.basic.ThreadUnsafe.lambda$unsafeMap$1(ThreadUnsafe.java:50)
     * 	at java.lang.Thread.run(Thread.java:748)
     *
     */
    public static void unsafeMap() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        System.out.println(map.size());
    }
}

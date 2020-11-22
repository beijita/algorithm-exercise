package com.fanzs.basic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemo {
    ThreadLocal<AtomicInteger> count = new ThreadLocal<>();

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();


    }
}

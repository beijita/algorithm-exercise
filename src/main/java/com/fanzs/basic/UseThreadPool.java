package com.fanzs.basic;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseThreadPool {
    public static void main(String[] args) {

        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(10);
        Executors.newSingleThreadExecutor();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>() {
        });
    }
}

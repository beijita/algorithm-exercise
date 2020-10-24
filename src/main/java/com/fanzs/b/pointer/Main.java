package com.fanzs.b.pointer;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; ; ++i) {
            int x = i;
            new Thread(() ->{
                System.out.println(x);
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

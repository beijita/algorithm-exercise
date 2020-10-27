package com.fanzs.basic;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                synchronousQueue.put("a");
                System.out.println(" synchronousQueue.put a");

                synchronousQueue.put("b");
                System.out.println(" synchronousQueue.put b");

                synchronousQueue.put("c");
                System.out.println(" synchronousQueue.put c");
            } catch (InterruptedException e) {
                System.out.println(" synchronousQueue.put 失败 ");
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println("take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);

                System.out.println("take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);

                System.out.println("take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

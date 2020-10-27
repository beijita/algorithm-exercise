package com.fanzs.basic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    /**
     * desc
     * 先唤醒两次，然后阻塞两次
     *
     * result
     * thread a start.
     * thread b start.
     * thread b 唤醒 a 一次
     * thread b 唤醒 a 两次
     * thread a 被唤醒1次
     */
    private static void test3(){
        Thread a = new Thread(() -> {
            System.out.println("thread a start.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
            LockSupport.park();
            System.out.println("thread a 被唤醒1次");
            LockSupport.park();
            System.out.println("thread a 被唤醒2次");
        }, "A");
        a.start();


        Thread b = new Thread(() -> {
            System.out.println("thread b start.");
            LockSupport.unpark(a);
            System.out.println("thread b 唤醒 a 一次");
            LockSupport.unpark(a);
            System.out.println("thread b 唤醒 a 两次");

        }, "B");
        b.start();
    }

    /**
     * desc 先唤醒后阻塞
     *
     * result
     *  thread a start.
     *  thread b start.
     *  thread b 唤醒 a
     *  thread a 被唤醒
     *
     *  Process finished with exit code 0
     */
    private static void test2(){
        Thread a = new Thread(() -> {
            System.out.println("thread a start.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
            LockSupport.park();
            System.out.println("thread a 被唤醒");
        }, "A");
        a.start();


        Thread b = new Thread(() -> {
            System.out.println("thread b start.");
            LockSupport.unpark(a);
            System.out.println("thread b 唤醒 a");
        }, "B");
        b.start();
    }

    private static void test1(){
        Thread a = new Thread(() -> {
            System.out.println("thread a start.");
            LockSupport.park();
            System.out.println("thread a 被唤醒");
        }, "A");
        a.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
        Thread b = new Thread(() -> {
            System.out.println("thread b start.");
            LockSupport.unpark(a);
            System.out.println("thread b 唤醒 a");
        }, "B");
        b.start();
    }
}

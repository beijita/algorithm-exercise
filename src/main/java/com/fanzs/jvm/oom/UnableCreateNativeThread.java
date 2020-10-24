package com.fanzs.jvm.oom;

public class UnableCreateNativeThread {
    /**
     * oom 异常
     * 不能创建过多的线程
     *
     * @param args
     * @throws InterruptedException
     */
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

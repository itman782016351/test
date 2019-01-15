package com.ideal.multithreading;

import java.util.Set;

/**
 * @author zhaopei
 * @create 2019-01-15 17:08
 */
public class TestVolatile extends Thread {
    //    private boolean isRunning = true;
    private volatile boolean isRunning = true;
    ;

    @Override
    public void run() {
        while (isRunning) {
        }
        System.out.println(" t1 stop......");
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public static void main(String[] args) throws InterruptedException {
        TestVolatile t1 = new TestVolatile();
        t1.start();
        Thread.sleep(1000);
        t1.setRunning(false);
    }
}

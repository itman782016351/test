package com.ideal.multithreading;

/**
 * @author zhaopei
 * @create 2019-03-06 13:41
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        NThread nThread = new NThread();
        nThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupt执行前" + System.currentTimeMillis());
        nThread.interrupt();
    }

    /**
     * 测试多线程的中断机制
     */
    static class NThread extends Thread {

        @Override
        public void run() {
            while (!interrupted()) {
                System.out.println("依然存活...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
//                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("interrupt执行后" + System.currentTimeMillis());
        }
    }

}

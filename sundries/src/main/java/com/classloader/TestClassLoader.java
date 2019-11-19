package com.classloader;

/**
 * @author zhaopei
 * @create 2019-10-25 17:34
 */
public class TestClassLoader {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "===" + Thread.currentThread().getContextClassLoader());
            }
        });
        t.start();
    }

}

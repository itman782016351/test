package com.ideal.multithreading;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author zhaopei
 * @create 2019-01-15 15:35
 */
public class TestMultiThreading {
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Test
    public void testThreadLocal() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                threadLocal.set("value1");
                System.out.println("线程t1取出的数据为：" + threadLocal.get());
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("t2开始执行");
                System.out.println("线程t2取出的数据为：" + threadLocal.get());
            }
        };
        t1.start();
        Thread.sleep(2000);
        t2.start();
    }

}

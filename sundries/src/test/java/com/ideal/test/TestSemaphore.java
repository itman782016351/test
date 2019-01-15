package com.ideal.test;

import java.util.concurrent.Semaphore;

import org.junit.Test;

public class TestSemaphore {
	private Semaphore semaphore = new Semaphore(0,true);
    @Test
    public void driveCar() {
        try {
        	semaphore.release();
            // 从信号量中获取一个允许机会
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " start at " + System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " stop at " + System.currentTimeMillis());
            // 释放允许，将占有的信号量归还
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
}

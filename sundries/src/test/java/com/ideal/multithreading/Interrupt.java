package com.ideal.multithreading;

/**
 * @author zhaopei
 * @create 2019-03-09 10:32
 */
public class Interrupt {
    public static void main(String[] args) {
        A a = new A();
        Thread t1 = new Thread(a);
        t1.start();

        System.out.println("执行睡眠之前1：" + t1.isInterrupted());
        try {
            System.out.println("执行睡眠之前2：" + t1.isInterrupted());
            t1.sleep(1000);//线程进入阻塞状态
            t1.interrupt();
        } catch (InterruptedException e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            System.out.println("执行睡眠之后：" + t1.isInterrupted());
        }
    }
}

class A implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread());
    }
}



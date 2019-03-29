package com.ideal.multithreading;

//线程通信。如下的三个关键字使用的话，都得在同步代码块或同步方法中。
//wait():一旦一个线程执行到wait()，就释放当前的锁。
//notify()/notifyAll():唤醒wait的一个或所有的线程
//使用两个线程打印 1-100. 线程1, 线程2 交替打印


class Flag {
    public static boolean flag = true;
}

class T1 implements Runnable {

    @Override
    public void run() {
        synchronized (Flag.class) {
            while (Flag.flag) {
                try {
                    Flag.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("t1 stop .......");
    }
}

class T2 implements Runnable {

    @Override
    public void run() {
        synchronized (Flag.class) {
            Flag.flag = false;
            System.out.println("notified other thread ...");
            Flag.class.notify();
        }

    }
}

public class TestCommunication2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        t1.start();
        Thread.sleep(2000);
        t2.start();
        ;
    }
}

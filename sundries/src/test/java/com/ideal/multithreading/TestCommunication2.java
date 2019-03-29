package com.ideal.multithreading;

//�߳�ͨ�š����µ������ؼ���ʹ�õĻ���������ͬ��������ͬ�������С�
//wait():һ��һ���߳�ִ�е�wait()�����ͷŵ�ǰ������
//notify()/notifyAll():����wait��һ�������е��߳�
//ʹ�������̴߳�ӡ 1-100. �߳�1, �߳�2 �����ӡ


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

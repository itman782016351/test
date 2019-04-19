package com.leetcode.zhaopei.test;

/**
 * @author zhaopei
 * @create 2019-04-19 9:28
 */
public class Synchronized {

    class Test {
        public synchronized void testFirst() {
            print("testFirst");
        }

        public void testSecond() {
            synchronized (this) {
                print("testSecond");
            }
        }

        public void testThird() {
            synchronized (Test.class) {
                print("testThird");
            }
        }

        public void print(String method) {
            System.out.println(method + "start");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(method + "end");
        }
    }


    class TestThread extends Thread {

        public int mType = 0;
        public Test mTest = null;

        public TestThread(int type, Test test) {
            this.mType = type;
            this.mTest = test;
        }

        public void run() {
            if (mTest == null) {
                if (mType == 1) {
                    Test test = new Test();
                    test.testFirst();
                } else if (mType == 2) {
                    Test test = new Test();
                    test.testSecond();
                } else if (mType == 3) {
                    Test test = new Test();
                    test.testThird();
                }
            } else {
                if (mType == 1) {
                    mTest.testFirst();
                } else if (mType == 2) {
                    mTest.testSecond();
                } else if (mType == 3) {
                    mTest.testThird();
                }
            }
        }
    }

    public static void main(String[] args) {
        Synchronized syn = new Synchronized();
        //  Test test = syn.new Test();
        for (int i = 0; i < 5; ++i) {
            syn.new TestThread(3, null).start();
        }
    }
}


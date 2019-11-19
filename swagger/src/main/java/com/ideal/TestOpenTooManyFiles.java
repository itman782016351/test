package com.ideal;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaopei
 * @create 2019-10-29 16:16
 */
public class TestOpenTooManyFiles {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000000; i++) {
            Thread t = new Thread(new Task());
            executorService.submit(t);
            System.out.println("提交了第" + i + "个任务");
        }
//        executorService.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            File f = new File("C:\\Users\\zhaopei\\Desktop\\GreenChannelChargeSupport.log");
            System.out.println(f);
        }
    }
}

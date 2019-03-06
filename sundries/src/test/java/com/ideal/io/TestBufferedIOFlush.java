package com.ideal.io;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaopei
 * @create 2019-01-14 17:35
 */
public class TestBufferedIOFlush {

    @Test
    public void testPrintWriter() throws Exception {
        File file = new File("1.txt");
        PrintWriter pw = new PrintWriter(file);
        String str = "abcdefghijklmnopqrstuvwxyz";
        int num = 4000;
        CountDownLatch latch = new CountDownLatch(num);
        ExecutorService threadPool = Executors.newFixedThreadPool(500);
        for (int i = 0; i < num; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    pw.println(str);
                    latch.countDown();
                    System.out.println("打印第" + latch.getCount() + "行");
                }
            };
            threadPool.submit(thread);
        }
        if (latch.await(10, TimeUnit.MINUTES)) {
            System.out.println("超时等待10分钟，关闭pw流...");
            pw.close();
            threadPool.shutdown();
        }
//        pw.close();
        System.out.println("finished...");
    }

    public static void main(String[] args) throws  Exception{
        File file = new File("1.txt");
        PrintWriter pw = new PrintWriter(file);
        String str = "abcdefghijklmnopqrstuvwxyz";
        int num = 100000;
        CountDownLatch latch = new CountDownLatch(num);
        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < num; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    pw.println(str);
                    latch.countDown();
                    System.out.println("打印第" + latch.getCount() + "行");
                }
            };
            threadPool.submit(thread);
        }
        if (latch.await(1, TimeUnit.MINUTES)) {
            System.out.println("超时等待10分钟，关闭pw流...");
            pw.close();
            threadPool.shutdown();
        }
//        pw.close();
        System.out.println("finished...");
    }

    @Test
    public void testLineReader() throws Exception {
        String location = "C:\\Users\\zhaopei\\Desktop\\33cvs.txt";
        File file = new File(location);
        FileReader fileReader = new FileReader(file);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        lineNumberReader.setLineNumber(2);
        System.out.println(lineNumberReader.readLine());
        System.out.println(lineNumberReader.getLineNumber());
    }

    @Test
    public void TestFileWrite() throws Exception {
        File file = new File("C:\\Users\\zhaopei\\Desktop\\aa.txt");
        for (int i = 0; i < 10; i++) {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("dddbbbb" + i);
            fileWriter.flush();
            fileWriter.close();
            File newFile = new File("C:\\Users\\zhaopei\\Desktop\\bb" + i + ".txt");
            System.out.println(file.renameTo(newFile));
            if (i == 2) {
                System.out.println("删除第二个文件" + newFile.delete());
            }
        }

        //  System.out.println("delete file ......"+newFile.delete());
    }
}

package com.ideal.hystrix.HelloWorld;

/**
 * @author zhaopei
 * @create 2019-03-30 22:40
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        HelloCommand command = new HelloCommand();
        String result = command.execute();
        System.out.println(result);
    }
}
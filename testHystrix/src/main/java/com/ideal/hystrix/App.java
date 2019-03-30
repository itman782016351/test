package com.ideal.hystrix;

/**
 * @author zhaopei
 * @create 2019-03-30 22:40
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            HelloCommand command = new HelloCommand();
            String result = command.execute();
            System.out.println("circuit Breaker is open : " + command.isCircuitBreakerOpen());
            if (command.isCircuitBreakerOpen()) {
                Thread.currentThread().sleep(500);
            }
        }
    }
}

package com.ideal.hystrix.HelloWorld;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author zhaopei
 * @create 2019-03-30 22:37
 */
public class HelloCommand extends HystrixCommand<String> {
    protected HelloCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("test"))
        );
    }

    @Override
    protected String run() throws Exception {
        //模拟请求外部接口需要的时间长度
        Thread.sleep(500);
        return "sucess";
    }

    @Override
    protected String getFallback() {
        //当外部请求超时后，会执行fallback里的业务逻辑
        System.out.println("执行了回退方法");
        return "error";
    }
}

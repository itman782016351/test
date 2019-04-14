package com.ideal.jvmoperation;

import org.junit.Test;

/**
 * @author zhaopei
 * @create 2019-04-13 20:49
 */
public class TestOperation {

    @Test
    public void testOperation() {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i++ + i * i++;
        int m = 3;
        int n = (m++) + (m++) + (m++);
        System.out.println(m);
        System.out.println(n);
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}

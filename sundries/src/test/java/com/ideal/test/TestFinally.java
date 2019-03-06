package com.ideal.test;

import org.junit.Test;

/**
 * @author zhaopei
 * @create 2019-01-18 13:44
 */
public class TestFinally {
    @Test
    public void testFinally() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        } finally {
            System.out.println("==========");
        }
    }

}

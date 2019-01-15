package com.ideal.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author zhaopei
 * @create 2019-01-05 19:29
 */
public class TestInvoke {


    @Test
    public void testInvoke() throws Exception {
        Class clz = Class.forName("com.ideal.reflect.Add");
        Add add = (Add) clz.newInstance();
        Method method = clz.getMethod("add");
        Object ret = method.invoke(add);
        System.out.println(ret.toString());
    }
}

class Add {
    public int add() {
        return 1 + 3;
    }
}

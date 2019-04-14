package com.ideal.innerClassTest;

import org.junit.Test;

/**
 * @author zhaopei
 * @create 2019-04-10 11:41
 */
public class TestInnerClass {

    @Test
    public void test1() {
        String var1 = "invoke innerClass";
        test2(new InnerInterface() {
            @Override
            public String method1() {
//                return va1 + "haha";
                return null;
            }
        });
    }

    String test2(InnerInterface innerInterface) {
        return innerInterface.method1();
    }
}
